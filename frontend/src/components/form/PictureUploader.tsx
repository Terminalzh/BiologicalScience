import { Input, notificationService, useColorMode } from "@hope-ui/solid";
import {
  ErrorBoundary,
  JSX,
  Match,
  Switch,
  createEffect,
  createMemo,
  createResource,
  createSignal,
  untrack,
} from "solid-js";
import { PictureTarget, Pictures, uploadPicture } from "~/utils/pictures";
import { LoadingIcon } from "../LoadingIcon";
import { MaterialSymbolsClose } from "../CloseIcon";
import { catchResource } from "~/utils";

interface PictureUploaderProps {
  name?: string;
  target?: PictureTarget;
  required?: boolean;
  value?: Pictures;
  file?: Blob;
  ref?: any; // 此处的Ref是选择文件的input的ref，用来实现在外部的click调起选择图片的操作
  onChanged?: (value: Pictures) => void; // 每次值改变时，并且非空时调用
}

function Icon(props: { icon: string }) {
  return (
    <div class="flex w-full h-full justify-center items-center absolute z-10">
      <Switch>
        <Match when={props.icon === "loading"}>
          <LoadingIcon class="text-secondary" />
        </Match>
        <Match when={props.icon === "upload"}>
          <IcRoundFileUpload class="text-secondary" />
        </Match>
        <Match when={props.icon === "close"}>
          <MaterialSymbolsClose class="text-secondary" />
        </Match>
      </Switch>
    </div>
  );
}

function PrevImage(props: { src?: Pictures; target?: PictureTarget }) {
  return (
    <img
      src={props.src ? props.src![props.target || "m"] : undefined}
      class="w-full h-full object-contain absolute z-10"
    />
  );
}

export function IcRoundFileUpload(props: JSX.IntrinsicElements["svg"]) {
  return (
    <svg
      xmlns="http://www.w3.org/2000/svg"
      width="2em"
      height="2em"
      viewBox="0 0 24 24"
      {...props}
    >
      <path
        fill="currentColor"
        d="M7.4 10h1.59v5c0 .55.45 1 1 1h4c.55 0 1-.45 1-1v-5h1.59c.89 0 1.34-1.08.71-1.71L12.7 3.7a.996.996 0 0 0-1.41 0L6.7 8.29c-.63.63-.19 1.71.7 1.71zM5 19c0 .55.45 1 1 1h12c.55 0 1-.45 1-1s-.45-1-1-1H6c-.55 0-1 .45-1 1z"
      ></path>
    </svg>
  );
}

/**
 * 在一个Form中有两个ImageUploader时会有错误，
 * 因为这里的input id只有一个
 * @returns
 */
export default function PictureUploader(props: PictureUploaderProps) {
  const [file, selectFile] = createSignal<Blob | undefined>();
  const [data] = createResource(file, uploadPicture, {});
  const [pictures, setPictures] = createSignal<Pictures>();
  const { colorMode } = useColorMode();

  const dataResult = catchResource(data, (e) => {
    untrack(() => {
      notificationService.show({
        status: "danger",
        title: "上传失败",
        description: e.message,
      });
    });
  });

  createEffect(() => {
    selectFile((_) => props.file);
  });

  const pictureStr = createMemo(() =>
    pictures() ? JSON.stringify(pictures()) : undefined
  );

  createEffect(() => {
    if (props.value) {
      setPictures(props.value);
    }
  });

  createEffect(() => {
    const value = dataResult();
    if (value) {
      setPictures(value);
    }
  });

  createEffect(() => {
    if (pictures()) {
      props.onChanged?.call(null, pictures()!);
    }
  });

  return (
    <div
      classList={{
        dark: colorMode() === "dark",
        light: colorMode() === "light",
      }}
    >
      <label
        for={props.name || "img-uploader"}
        class="relative flex w-5rem h-5rem light:bg-black/5 dark:bg-white/5 rounded-3xl overflow-hidden"
        classList={{
          "cursor-pointer": !data.loading,
          "cursor-not-allowed": data.loading,
        }}
      >
        <Switch>
          <Match
            when={
              (pictures() !== undefined || props.value !== undefined) &&
              !data.loading
            }
          >
            <ErrorBoundary fallback={() => <Icon icon="close" />}>
              <PrevImage src={pictures()} />
            </ErrorBoundary>
          </Match>
          <Match
            when={
              data.loading ||
              data.state === "ready" ||
              data.state === "errored" ||
              data.state === "unresolved"
            }
          >
            <Switch>
              <Match when={data.loading}>
                <Icon icon="loading" />
              </Match>
              <Match
                when={data.state === "ready" || data.state === "unresolved"}
              >
                <Icon icon="upload" />
              </Match>
            </Switch>
          </Match>
        </Switch>
        <input
          name={props.name}
          type="text"
          class="opacity-0 absolute w-full h-full z--1 box-border"
          value={pictureStr()}
          required={props.required}
        />
        <input
          ref={props.ref}
          id={props.name || "img-uploader"}
          type="file"
          class="hidden"
          disabled={data.loading}
          accept="image/png, image/jpeg"
          onChange={(e) => {
            if (e.target.files?.length || 0 > 0) {
              const file = e.target.files!![0];
              selectFile(() => file);
            }
          }}
        />
      </label>
    </div>
  );
}
