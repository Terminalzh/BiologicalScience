import { createForm } from "@felte/solid";
import {
  Anchor,
  Button,
  FormControl,
  FormLabel,
  HStack,
  Image,
  Input,
  Radio,
  RadioGroup,
  notificationService,
} from "@hope-ui/solid";
import {
  Show,
  catchError,
  createEffect,
  createMemo,
  createResource,
  createSignal,
  untrack,
} from "solid-js";
import { useSearchParams } from "solid-start";
import { login, register } from "~/api/user";
import PictureUploader from "~/components/form/PictureUploader";
import { catchResource } from "~/utils";

const LoginForm = () => {
  const [data, setData] = createSignal();
  const [loginResource] = createResource(data, login);
  const loginResult = catchResource(loginResource, (e) => {
    untrack(() => {
      notificationService.show({
        title: "登录失败",
        status: "danger",
        description: e.message,
      });
    });
  });

  createEffect(() => {
    if (loginResult()) {
      untrack(() => {
        notificationService.show({
          title: "登录成功",
          status: "success",
        });
      });
    }
  });
  const { form } = createForm({
    onSubmit(values) {
      setData(values);
    },
  });
  return (
    <form ref={form} class="flex flex-col gap-6 mt-4">
      <FormControl required>
        <FormLabel>用户名</FormLabel>
        <Input type="text" name="username" placeholder="用户名或邮箱" />
      </FormControl>

      <FormControl required>
        <FormLabel>密码</FormLabel>
        <Input type="password" name="password" placeholder="输入密码" />
      </FormControl>
      <div class="flex gap-4">
        <Button
          loading={loginResource.loading}
          type="submit"
          class="flex-1 btn"
        >
          登录
        </Button>
      </div>
    </form>
  );
};

const RegistryForm = () => {
  const [data, setData] = createSignal();

  const [registerResource] = createResource(data, register);
  let avatar = "";
  const registerResult = catchResource(registerResource, (e) => {
    untrack(() => {
      notificationService.show({
        title: "注册失败",
        status: "danger",
        description: e.message,
      });
    });
  });

  createEffect(() => {
    if (registerResult()) {
      untrack(() => {
        notificationService.show({
          title: "注册成功",
          status: "success",
        });
      });
    }
  });

  const { form } = createForm({
    onSubmit: (values) => {
      values.avatar = avatar;
      setData(values);
    },
  });
  return (
    <form ref={form} class="flex flex-col gap-6 mt-4">
      <div class="flex gap-8 items-center">
        <div>
          <PictureUploader
            name="avatar"
            required
            onChanged={(value) => {
              avatar = JSON.stringify(value);
            }}
          />
        </div>
        <div class="flex flex-col gap-4">
          <FormControl required>
            <FormLabel>姓名</FormLabel>
            <Input type="text" name="name" placeholder="输入您的姓名" />
          </FormControl>

          <FormControl required>
            <FormLabel>密码</FormLabel>
            <Input type="password" name="password" placeholder="输入密码" />
          </FormControl>
        </div>
      </div>

      <FormControl required>
        <FormLabel>性别</FormLabel>
        <RadioGroup defaultValue="男" name="gender">
          <HStack spacing="$4">
            <Radio value="男">男</Radio>
            <Radio value="女">女</Radio>
          </HStack>
        </RadioGroup>
      </FormControl>
      <FormControl required>
        <FormLabel>电话号码</FormLabel>
        <Input type="tel" name="phone" placeholder="输入您的电话号码" />
      </FormControl>
      <FormControl required>
        <FormLabel>电子邮箱</FormLabel>
        <Input type="email" name="email" placeholder="feedback@mammalia.org" />
      </FormControl>
      <div class="flex gap-4">
        <Button
          loading={registerResource.loading}
          type="submit"
          class="flex-1 btn"
        >
          立即注册
        </Button>
      </div>
    </form>
  );
};

export default function LoginPage() {
  const [searchParams] = useSearchParams();
  const isLoginMode = createMemo(() => searchParams.registry !== "true");
  return (
    <section class="min-h-65vh flex justify-center items-center">
      <div class="border-2 light:border-black/5 dark:border-white/5 rounded-std p-8 light:bg-white/80 dark:bg-dark/80 min-w-sm">
        <div>
          <h1 class="font-bold font-sans text-3xl text-primary">
            {isLoginMode() ? "登录" : "注册"}
            <span class="font-brand text-xl ml-2 text-brand-primary/87">
              Mammalia
            </span>
          </h1>
        </div>
        <Show when={isLoginMode()}>
          <LoginForm />
        </Show>

        <Show when={isLoginMode()} fallback={<RegistryForm />}>
          <p class="text-center mt-4">
            <Anchor>忘记密码</Anchor>
          </p>
        </Show>
      </div>
    </section>
  );
}
