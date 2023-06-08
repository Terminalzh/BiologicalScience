import {
  ErrorBoundary,
  For,
  JSX,
  Show,
  Suspense,
  createEffect,
  createResource,
  createSignal,
  untrack,
} from "solid-js";
import Pagination from "./Pagination";
import {
  PaginationEntity,
  PaginationParams,
  deletes,
  getTableData,
} from "~/api/base";
import {
  Button,
  ButtonGroup,
  IconButton,
  Popover,
  PopoverBody,
  PopoverContent,
  PopoverFooter,
  PopoverHeader,
  PopoverTrigger,
  Spinner,
  TableCaption,
  Tbody,
  Td,
  Th,
  Thead,
  Tr,
  createDisclosure,
  notificationService,
} from "@hope-ui/solid";
import { Table as HopeTable } from "@hope-ui/solid";
import { catchResource } from "~/utils";
import {
  Modal,
  ModalBody,
  ModalCloseButton,
  ModalContent,
  ModalFooter,
  ModalHeader,
  ModalOverlay,
} from "@hope-ui/solid";

/**
 * Column代表着表格中的一个字段，默认以string的类型展示
 */
export interface Column {
  // 这个Column的标题
  title: string;
  // 它在data或者api中的key
  value: (record: any) => string;

  // 相对宽度，范围[0, 1]
  width?: number;

  /**
   * 可以用一个组件来替换展示的方式
   * @param data 当前行的数据
   * @returns 一个JXS.Element组件
   */
  component?: (data: any) => JSX.Element;
}

export interface TopCaptions {
  /**
   * 当创建按钮点击时调用，如果为undefined，则不显示创建按钮
   * 默认事件为打开 行编辑窗口
   *
   * @returns 是否拦截此次点击事件，也就是说，当返回true时，这个按钮的默认事件将不会触发
   */
  createButton?: () => boolean;

  customElements?: Array<JSX.Element>;
}

/**
 * 对于一个表格组件，如果通过api提供数据源，那么data可以为空，反之亦然。
 * T: 数据项的类型
 */
export interface TableProps<T> {
  name?: string;
  columns: Array<Column>;

  // 是否可以进行删除或者编辑操作
  operations?:
    | boolean
    | {
        delete?: boolean;
        edit?: boolean;
      };

  topCaptions?: TopCaptions;

  // 数据源头的API
  api?: string;

  params?: any;

  // 当编辑确认时调用
  onEdit?: (data: T) => void;

  // 当删除确认时调用，默认会在api后追加id自动调用删除，比如，当点击删除后会以Delete的方法调用/api/v1/blogs/:id
  onDelete?: (data: T) => Promise<any>;

  /**
   * 行编辑器组件，这个组件位于行编辑窗口内
   *
   * @param data 若为undefined，则此时的功能为新建行，否则为修改行
   * @returns
   */
  itemEditor?: (data?: T, onClose?: () => void) => JSX.Element;

  onItemClick?: (data?: T) => void;
}

export function IcOutlineSync(props: JSX.IntrinsicElements["svg"]) {
  return (
    <svg
      xmlns="http://www.w3.org/2000/svg"
      width="1.3em"
      height="1.3em"
      viewBox="0 0 24 24"
      {...props}
    >
      <path
        fill="#ffffff"
        d="M12 4V1L8 5l4 4V6c3.31 0 6 2.69 6 6c0 1.01-.25 1.97-.7 2.8l1.46 1.46A7.93 7.93 0 0 0 20 12c0-4.42-3.58-8-8-8zm0 14c-3.31 0-6-2.69-6-6c0-1.01.25-1.97.7-2.8L5.24 7.74A7.93 7.93 0 0 0 4 12c0 4.42 3.58 8 8 8v3l4-4l-4-4v3z"
      ></path>
    </svg>
  );
}

const Loading = () => {
  return (
    <div class="h-full flex gap-3 items-center justify-center py-10 flex-col">
      <Spinner />
    </div>
  );
};

const TableColumnCell = (props: { col: Column; row: any }) => {
  const { col, row } = props;
  return (
    <Td>
      <Show
        when={col.component}
        fallback={
          <p class="line-clamp-2" title={col.value(row)}>
            {col.value(row)}
          </p>
        }
      >
        {col.component?.call(null, col.value(row))}
      </Show>
    </Td>
  );
};

const TableBody = <T,>(props: TableProps<T>) => {
  const { columns, onEdit, onDelete, operations, api } = props;
  const [pageParam, setPageParam] = createSignal<PaginationParams>({
    pageNum: 1,
    pageSize: 10,
  });

  const [page, setPage] = createSignal<PaginationEntity>();

  const fetchData = async (
    pagination: Partial<PaginationEntity>
  ): Promise<Array<any>> => {
    return new Promise((resolve, reject) => {
      getTableData(api || "", Object.assign({}, pagination, props.params))
        .then((res) => {
          setPage({
            pageNum: res.pageNum,
            pageSize: res.pageSize,
            total: res.pages,
          });
          resolve(res.list);
        })
        .catch((e) => {
          reject(e.message);
        });
    });
  };

  const [dataResource, { mutate, refetch }] = createResource(
    pageParam,
    fetchData
  );

  const afterEditingOrCreating = () => {
    refetch();
  };

  const [deleteId, setDeleteId] = createSignal<string>();
  const [deleteItemResource] = createResource(deleteId, deletes);
  const deleteItemResult = catchResource(deleteItemResource, (e) => {
    untrack(() => {
      notificationService.show({
        title: "删除失败",
        status: "danger",
        description: e.message,
      });
    });
  });

  createEffect(() => {
    if (deleteItemResult() !== undefined) {
      untrack(() => {
        notificationService.show({
          title: "删除成功",
          status: "success",
        });
        refetch();
      });
    }
  });

  const { isOpen, onOpen, onClose } = createDisclosure();
  const [editCurrent, setEditCurrent] = createSignal<T | undefined>();

  return (
    <>
      <HopeTable striped="odd" highlightOnHover dense>
        <TableCaption class="caption-top">
          <div class="flex justify-end gap-4 items-center">
            <For each={props.topCaptions?.customElements}>{(item) => item}</For>

            <Show when={props.topCaptions?.createButton}>
              <Button
                class="btn"
                onClick={() => {
                  if (!props.topCaptions?.createButton?.call(null)) {
                    setEditCurrent(undefined);
                    onOpen();
                  }
                }}
              >
                创建
              </Button>
            </Show>

            <IconButton
              class="btn"
              aria-label="Refresh"
              icon={<IcOutlineSync />}
              onClick={(e: any) => {
                e.stopPropagation();
                refetch();
              }}
            ></IconButton>
          </div>
        </TableCaption>
        <TableCaption class="caption-bottom">
          <Show when={page()}>
            {(value) => (
              <Pagination pagination={value()} pageSetter={setPageParam} />
            )}
          </Show>
        </TableCaption>
        <Thead>
          <Tr>
            <For each={columns}>{(item) => <Th>{item.title}</Th>}</For>
            <Show when={operations}>
              <Th class="text-center w-1/10">操作</Th>
            </Show>
          </Tr>
        </Thead>
        <Tbody>
          <For each={dataResource()}>
            {(row) => (
              <Tr
                onClick={(e: any) => {
                  if (props.onItemClick) {
                    e.stopPropagation();
                    props.onItemClick?.call(null, row);
                  }
                }}
              >
                <For each={columns}>
                  {(col) => <TableColumnCell col={col} row={row} />}
                </For>
                <Show when={operations}>
                  <Td>
                    <div class="w-full flex gap-3 items-center justify-center">
                      <Show
                        when={
                          operations !== true
                            ? (operations as any).edit
                            : operations
                        }
                      >
                        <Button
                          size="sm"
                          class="bg-brand-primary/80"
                          onClick={() => {
                            setEditCurrent(row);
                            onOpen();
                          }}
                        >
                          编辑
                        </Button>
                      </Show>
                      <Show
                        when={
                          operations !== true
                            ? (operations as any).delete
                            : operations
                        }
                      >
                        <Popover>
                          <PopoverTrigger as={Button} size="sm" variant="ghost">
                            删除
                          </PopoverTrigger>
                          <PopoverContent maxW="$xs">
                            <PopoverHeader>
                              确定要删除该条记录吗？
                            </PopoverHeader>
                            <PopoverFooter class="justify-end">
                              <Button
                                size="sm"
                                loading={deleteItemResource.loading}
                                colorScheme="danger"
                                onClick={async () => {
                                  if (onDelete) {
                                    await onDelete(row);
                                    refetch();
                                  } else {
                                    setDeleteId(`${api}/${row.id}`);
                                  }
                                }}
                              >
                                确定
                              </Button>
                            </PopoverFooter>
                          </PopoverContent>
                        </Popover>
                      </Show>
                    </div>
                  </Td>
                </Show>
              </Tr>
            )}
          </For>
        </Tbody>
      </HopeTable>
      <Modal scrollBehavior="inside" opened={isOpen()} onClose={onClose}>
        <ModalOverlay />
        <ModalContent>
          {props.itemEditor?.call(null, editCurrent(), onClose)}
        </ModalContent>
      </Modal>
    </>
  );
};

export function Table<T>(props: TableProps<T>) {
  return (
    <div {...props}>
      <ErrorBoundary fallback={(err) => <p>{err.toString()}</p>}>
        <Suspense fallback={<Loading />}>
          <TableBody {...props} />
        </Suspense>
      </ErrorBoundary>
    </div>
  );
}
