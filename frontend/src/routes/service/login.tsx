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
import { useNavigate, useSearchParams } from "solid-start";
import { login, register } from "~/api/user";
import RegistryForm from "~/components/RegistryForm";
import PictureUploader from "~/components/form/PictureUploader";
import { catchResource } from "~/utils";

const LoginForm = () => {
  const [data, setData] = createSignal();
  const [loginResource] = createResource(data, login);
  const navigate = useNavigate();
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
        navigate("/admin/charts");
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
      <FormControl required disabled={loginResource.loading}>
        <FormLabel>用户名</FormLabel>
        <Input type="text" name="username" placeholder="用户名或邮箱" />
      </FormControl>

      <FormControl required disabled={loginResource.loading}>
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

export default function LoginPage() {
  const [searchParams] = useSearchParams();
  const navigate = useNavigate();

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

        <Show
          when={isLoginMode()}
          fallback={
            <RegistryForm
              onFailed={(e) => {
                notificationService.show({
                  title: "注册失败",
                  status: "danger",
                  description: e.message,
                });
              }}
              onSucceed={() => {
                notificationService.show({
                  title: "注册成功",
                  status: "success",
                });
                navigate("/admin/charts");
              }}
            />
          }
        >
          <p class="text-center mt-4">
            <Anchor>忘记密码</Anchor>
          </p>
        </Show>
      </div>
    </section>
  );
}
