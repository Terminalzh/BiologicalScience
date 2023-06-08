import {
  Button,
  Input,
  Modal,
  ModalContent,
  ModalOverlay,
  Select,
  SelectContent,
  SelectIcon,
  SelectListbox,
  SelectOption,
  SelectOptionIndicator,
  SelectOptionText,
  SelectPlaceholder,
  SelectTrigger,
  SelectValue,
  Spinner,
  Text,
} from "@hope-ui/solid";
import {
  ErrorBoundary,
  For,
  JSX,
  Show,
  Suspense,
  createEffect,
  createResource,
  createSignal,
} from "solid-js";
import { getCategory } from "~/api/category";

interface IdNode {
  id: number;
  next?: IdNode;
}

const serializeIdNode2String = (node: IdNode) => {
  let current: IdNode | undefined = node;
  let result = `${current.id}`;
  while ((current = current.next)) {
    result += `.${current.id}`;
  }
  return result;
};

const CategoryItem = (props: {
  title: string;
  node: IdNode;
  children?: (node: IdNode) => JSX.Element;
}) => {
  const [id, setId] = createSignal<number>();
  createEffect(() => {
    setId(props.node.id);
  });
  const [selected, setSelected] = createSignal<IdNode>();
  const [categoryResource] = createResource(id, getCategory);

  return (
    <>
      <ErrorBoundary fallback={(e) => <div>Err: {e.message}</div>}>
        <Suspense
          fallback={
            <div class="flex items-center">
              <Spinner />
            </div>
          }
        >
          <Select
            onChange={(v) => {
              const node = { id: v };
              props.node.next = node;
              setSelected(node);
            }}
          >
            <SelectTrigger>
              <SelectPlaceholder>全部{props.title}</SelectPlaceholder>
              <SelectValue />
              <SelectIcon />
            </SelectTrigger>
            <SelectContent>
              <SelectListbox>
                <For each={categoryResource()}>
                  {(item) => (
                    <SelectOption
                      value={item.id}
                      textValue={`${item.latinName} ${
                        item.cName ? `（${item.cName}）` : ""
                      }`}
                    >
                      <SelectOptionText>{item.latinName}</SelectOptionText>
                      <Text>{item.cName}</Text>
                      <SelectOptionIndicator />
                    </SelectOption>
                  )}
                </For>
              </SelectListbox>
            </SelectContent>
          </Select>
        </Suspense>
      </ErrorBoundary>
      <Show when={selected()}>{props.children?.(selected()!)}</Show>
    </>
  );
};

export function SpeciesSearcher(props: { class?: string }) {
  let root: IdNode = { id: 1 };
  return (
    <div class={props.class}>
      <CategoryItem node={root} title="亚纲">
        {(node) => (
          <CategoryItem node={node} title="目">
            {(order) => (
              <CategoryItem node={order} title="科">
                {(family) => <CategoryItem node={family} title="属" />}
              </CategoryItem>
            )}
          </CategoryItem>
        )}
      </CategoryItem>
      {/* <Input
        type="text"
        placeholder="输入关键词搜索"
        onInput={(e) => {
          console.log(serializeIdNode2String(root));
        }}
      /> */}
    </div>
  );
}

export function SpeciesSelector(props: { onChanged: () => void }) {
  return (
    <div>
      <div>
        <p></p>
        <p></p>
      </div>
      <div>
        <Button>选择物种</Button>
      </div>

      <Modal>
        <ModalOverlay />
        <ModalContent></ModalContent>
      </Modal>
    </div>
  );
}
