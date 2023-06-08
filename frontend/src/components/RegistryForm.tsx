import { createForm } from "@felte/solid";
import {
  Button,
  FormControl,
  FormLabel,
  HStack,
  Input,
  Radio,
  RadioGroup,
} from "@hope-ui/solid";
import {
  createSignal,
  createResource,
  untrack,
  createEffect,
  Show,
} from "solid-js";
import { register, updateUser } from "~/api/user";
import { catchResource } from "~/utils";
import PictureUploader from "./form/PictureUploader";

const api = (data: { id?: string; data: any }) => {
  if (data.id) {
    return updateUser(data.id, data.data);
  } else {
    return register(data.data);
  }
};

export default function RegistryForm(props: {
  data?: any;
  name?: string;
  onFailed?: (e: Error) => void;
  onSucceed?: () => void;
  onSubmitted?: () => void;
}) {
  const [data, setData] = createSignal<{ id?: string; data: any }>();
  const [registerResource] = createResource(data, api);
  let avatar = "";
  const registerResult = catchResource(registerResource, (e) => {
    untrack(() => {
      props.onFailed?.(e);
    });
  });

  createEffect(() => {
    if (registerResult() !== undefined) {
      untrack(() => {
        props.onSucceed?.();
      });
    }
  });

  const { form } = createForm({
    onSubmit: (values) => {
      values.avatar = avatar;
      props?.onSubmitted?.();
      setData({ id: props.data?.id, data: values });
    },
  });

  return (
    <form ref={form} id={props.name} class="flex flex-col gap-6 mt-4">
      <div class="flex gap-8 items-center">
        <div>
          <PictureUploader
            name="avatar"
            required
            value={
              props.data?.avatar ? JSON.parse(props.data!.avatar) : undefined
            }
            onChanged={(value) => {
              avatar = JSON.stringify(value);
            }}
          />
        </div>
        <div class="flex flex-col gap-4">
          <FormControl required disabled={registerResource.loading}>
            <FormLabel>用户名</FormLabel>
            <Input
              type="text"
              name="name"
              placeholder="输入您的用户名"
              value={props.data?.name}
            />
          </FormControl>

          <FormControl required disabled={registerResource.loading}>
            <FormLabel>密码</FormLabel>
            <Input
              type="password"
              name="password"
              placeholder="输入密码"
              autocomplete="current-password"
              value={props.data?.password}
            />
          </FormControl>
        </div>
      </div>

      <FormControl required disabled={registerResource.loading}>
        <FormLabel>性别</FormLabel>
        <RadioGroup defaultValue={props.data?.gender || "男"} name="gender">
          <HStack spacing="$4">
            <Radio value="男">男</Radio>
            <Radio value="女">女</Radio>
          </HStack>
        </RadioGroup>
      </FormControl>
      <FormControl required disabled={registerResource.loading}>
        <FormLabel>电话号码</FormLabel>
        <Input
          type="tel"
          name="phone"
          placeholder="输入您的电话号码"
          value={props.data?.phone}
        />
      </FormControl>
      <FormControl required disabled={registerResource.loading}>
        <FormLabel>电子邮箱</FormLabel>
        <Input
          type="email"
          name="email"
          placeholder="feedback@mammalia.org"
          value={props.data?.email}
        />
      </FormControl>
      <Show when={!props.name}>
        <div class="flex gap-4">
          <Button
            loading={registerResource.loading}
            type="submit"
            class="flex-1 btn"
          >
            立即注册
          </Button>
        </div>
      </Show>
    </form>
  );
}
