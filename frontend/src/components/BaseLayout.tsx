import {
  Anchor,
  Avatar,
  Button,
  IconButton,
  Menu,
  MenuContent,
  MenuItem,
  MenuTrigger,
  Spinner,
  notificationService,
  useColorMode,
} from "@hope-ui/solid";
import {
  Accessor,
  ErrorBoundary,
  JSX,
  Show,
  Suspense,
  createMemo,
  createResource,
  untrack,
} from "solid-js";
import { useLocation, useNavigate } from "solid-start";
import { LogoIcon } from "~/components/LogoIcon";
import { User, getMe, logout } from "~/api/user";
import { catchResource } from "~/utils";

export function MaterialSymbolsDarkMode(props: JSX.IntrinsicElements["svg"]) {
  return (
    <svg
      xmlns="http://www.w3.org/2000/svg"
      width="1em"
      height="1em"
      viewBox="0 0 24 24"
      {...props}
    >
      <path
        fill="currentColor"
        d="M12 21q-3.75 0-6.375-2.625T3 12q0-3.75 2.625-6.375T12 3q.35 0 .688.025t.662.075q-1.025.725-1.638 1.888T11.1 7.5q0 2.25 1.575 3.825T16.5 12.9q1.375 0 2.525-.613T20.9 10.65q.05.325.075.662T21 12q0 3.75-2.625 6.375T12 21Z"
      ></path>
    </svg>
  );
}

export function MaterialSymbolsLightMode(props: JSX.IntrinsicElements["svg"]) {
  return (
    <svg
      xmlns="http://www.w3.org/2000/svg"
      width="1em"
      height="1em"
      viewBox="0 0 24 24"
      {...props}
    >
      <path
        fill="currentColor"
        d="M12 17q-2.075 0-3.538-1.463T7 12q0-2.075 1.463-3.538T12 7q2.075 0 3.538 1.463T17 12q0 2.075-1.463 3.538T12 17ZM2 13q-.425 0-.713-.288T1 12q0-.425.288-.713T2 11h2q.425 0 .713.288T5 12q0 .425-.288.713T4 13H2Zm18 0q-.425 0-.713-.288T19 12q0-.425.288-.713T20 11h2q.425 0 .713.288T23 12q0 .425-.288.713T22 13h-2Zm-8-8q-.425 0-.713-.288T11 4V2q0-.425.288-.713T12 1q.425 0 .713.288T13 2v2q0 .425-.288.713T12 5Zm0 18q-.425 0-.713-.288T11 22v-2q0-.425.288-.713T12 19q.425 0 .713.288T13 20v2q0 .425-.288.713T12 23ZM5.65 7.05L4.575 6q-.3-.275-.288-.7t.288-.725q.3-.3.725-.3t.7.3L7.05 5.65q.275.3.275.7t-.275.7q-.275.3-.687.288T5.65 7.05ZM18 19.425l-1.05-1.075q-.275-.3-.275-.713t.275-.687q.275-.3.688-.287t.712.287L19.425 18q.3.275.288.7t-.288.725q-.3.3-.725.3t-.7-.3ZM16.95 7.05q-.3-.275-.288-.687t.288-.713L18 4.575q.275-.3.7-.288t.725.288q.3.3.3.725t-.3.7L18.35 7.05q-.3.275-.7.275t-.7-.275ZM4.575 19.425q-.3-.3-.3-.725t.3-.7l1.075-1.05q.3-.275.712-.275t.688.275q.3.275.288.688t-.288.712L6 19.425q-.275.3-.7.288t-.725-.288Z"
      ></path>
    </svg>
  );
}

export function TablerBrandBilibili(props: JSX.IntrinsicElements["svg"]) {
  return (
    <svg
      xmlns="http://www.w3.org/2000/svg"
      width="2rem"
      height="2rem"
      viewBox="0 0 24 24"
      {...props}
    >
      <path
        fill="none"
        stroke="currentColor"
        stroke-linecap="round"
        stroke-linejoin="round"
        stroke-width={2}
        d="M3 10a4 4 0 0 1 4-4h10a4 4 0 0 1 4 4v6a4 4 0 0 1-4 4H7a4 4 0 0 1-4-4v-6zm5-7l2 3m6-3l-2 3m-5 7v-2m6 0v2"
      ></path>
    </svg>
  );
}

export function TablerBrandTiktok(props: JSX.IntrinsicElements["svg"]) {
  return (
    <svg
      xmlns="http://www.w3.org/2000/svg"
      width="2rem"
      height="2rem"
      viewBox="0 0 24 24"
      {...props}
    >
      <path
        fill="none"
        stroke="currentColor"
        stroke-linecap="round"
        stroke-linejoin="round"
        stroke-width={2}
        d="M21 7.917v4.034A9.948 9.948 0 0 1 16 10v4.5a6.5 6.5 0 1 1-8-6.326V12.5a2.5 2.5 0 1 0 4 2V3h4.083A6.005 6.005 0 0 0 21 7.917z"
      ></path>
    </svg>
  );
}

export function TablerBrandGithub(props: JSX.IntrinsicElements["svg"]) {
  return (
    <svg
      xmlns="http://www.w3.org/2000/svg"
      width="2rem"
      height="2rem"
      viewBox="0 0 24 24"
      {...props}
    >
      <path
        fill="none"
        stroke="currentColor"
        stroke-linecap="round"
        stroke-linejoin="round"
        stroke-width={2}
        d="M9 19c-4.3 1.4-4.3-2.5-6-3m12 5v-3.5c0-1 .1-1.4-.5-2c2.8-.3 5.5-1.4 5.5-6a4.6 4.6 0 0 0-1.3-3.2a4.2 4.2 0 0 0-.1-3.2s-1.1-.3-3.5 1.3a12.3 12.3 0 0 0-6.2 0C6.5 2.8 5.4 3.1 5.4 3.1a4.2 4.2 0 0 0-.1 3.2A4.6 4.6 0 0 0 4 9.5c0 4.6 2.7 5.7 5.5 6c-.6.6-.6 1.2-.5 2V21"
      ></path>
    </svg>
  );
}

export function TablerBrandWechat(props: JSX.IntrinsicElements["svg"]) {
  return (
    <svg
      xmlns="http://www.w3.org/2000/svg"
      width="2rem"
      height="2rem"
      viewBox="0 0 24 24"
      {...props}
    >
      <g
        fill="none"
        stroke="currentColor"
        stroke-linecap="round"
        stroke-linejoin="round"
        stroke-width={2}
      >
        <path d="M16.5 10c3.038 0 5.5 2.015 5.5 4.5c0 1.397-.778 2.645-2 3.47V20l-1.964-1.178A6.649 6.649 0 0 1 16.5 19c-3.038 0-5.5-2.015-5.5-4.5s2.462-4.5 5.5-4.5z"></path>
        <path d="M11.197 15.698c-.69.196-1.43.302-2.197.302a8.008 8.008 0 0 1-2.612-.432L4 17v-2.801C2.763 13.117 2 11.635 2 10c0-3.314 3.134-6 7-6c3.782 0 6.863 2.57 7 5.785v.233M10 8h.01M7 8h.01M15 14h.01M18 14h.01"></path>
      </g>
    </svg>
  );
}

const BrandItem = (props: { brand: JSX.Element }) => {
  return (
    <li class="hover:text-brand-primary/87 cursor-pointer transition-all">
      {props.brand}
    </li>
  );
};

const NavItem = (props: any) => {
  return (
    <li class="cursor-pointer hover:text-brand-primary/87 transition-all">
      <a href={props.target}>{props.children}</a>
    </li>
  );
};

export function SolarUserBoldDuotone(props: JSX.IntrinsicElements["svg"]) {
  return (
    <svg
      xmlns="http://www.w3.org/2000/svg"
      width="1em"
      height="1em"
      viewBox="0 0 24 24"
      {...props}
    >
      <g>
        <circle cx="12" cy="6" r="4"></circle>
        <path
          d="M20 17.5c0 2.485 0 4.5-8 4.5s-8-2.015-8-4.5S7.582 13 12 13s8 2.015 8 4.5Z"
          opacity=".5"
        ></path>
      </g>
    </svg>
  );
}

export function SolarLogout2BoldDuotone(props: JSX.IntrinsicElements["svg"]) {
  return (
    <svg
      xmlns="http://www.w3.org/2000/svg"
      width="1.2em"
      height="1.3em"
      viewBox="0 0 24 24"
      {...props}
    >
      <g>
        <path
          d="M16 2h-1c-2.829 0-4.242 0-5.121.879C9 3.758 9 5.172 9 8v8c0 2.829 0 4.243.879 5.122c.878.878 2.292.878 5.119.878H16c2.828 0 4.242 0 5.121-.879C22 20.243 22 18.828 22 16V8c0-2.828 0-4.243-.879-5.121C20.242 2 18.828 2 16 2Z"
          opacity=".5"
        ></path>
        <path
          fill-rule="evenodd"
          d="M15.75 12a.75.75 0 0 0-.75-.75H4.027l1.961-1.68a.75.75 0 1 0-.976-1.14l-3.5 3a.75.75 0 0 0 0 1.14l3.5 3a.75.75 0 1 0 .976-1.14l-1.96-1.68H15a.75.75 0 0 0 .75-.75Z"
          clip-rule="evenodd"
        ></path>
      </g>
    </svg>
  );
}

export interface BaseLayoutProps {
  mode: "normal" | "admin";
  children: (user: Accessor<User | undefined>) => JSX.Element;
}

export default function BaseLayout(props: BaseLayoutProps) {
  const { colorMode, toggleColorMode } = useColorMode();
  const [meResource] = createResource(getMe);
  const location = useLocation();
  const navigate = useNavigate();

  const userResult = catchResource(meResource, (e) => {
    if (props.mode === "admin") {
      untrack(() => {
        notificationService.show({
          title: "未登录",
          description: e.message,
          status: "danger",
        });
        navigate("/service/login");
      });
    }
  });

  const getAvatar = createMemo(() => {
    if (userResult()?.avatar) {
      try {
        const pictures = JSON.parse(userResult()!.avatar);
        return pictures.m;
      } catch (e) {}
    }
    return "";
  });

  return (
    <>
      <header
        class="fixed top-0 z-20 left-0 right-0"
        classList={{
          dark: colorMode() === "dark",
          light: colorMode() === "light",
        }}
      >
        <div class="backdrop-blur-md">
          <nav class="inline flex container-compact items-center py-4 border-b-solid border-b-2 light:border-b-black/5 dark:border-b-white/5">
            <div
              class="flex items-center gap-2 cursor-pointer"
              onClick={() => {
                navigate("/service");
              }}
            >
              <LogoIcon class="text-brand-primary" />
              <span class="flex-1 font-brand text-brand-primary text-2xl">
                Mammalia
              </span>
            </div>
            <Show when={props.mode === "normal"}>
              <ul class="flex font-sans gap-8 flex-1 items-center list-none justify-center font-500 text-lg">
                <NavItem target="/service">主页</NavItem>
                <NavItem target="/service/retrieval">物种检索</NavItem>
                <NavItem>每日推荐</NavItem>
                <NavItem>关于我们</NavItem>
              </ul>
            </Show>
            <div class="flex gap-8 flex-1 items-center justify-end">
              <ErrorBoundary
                fallback={() => (
                  <>
                    <Button
                      class="btn-outlined"
                      onClick={() => {
                        navigate("login");
                      }}
                    >
                      登录
                    </Button>
                    <Button
                      class="btn"
                      onClick={() => {
                        navigate("login?registry=true");
                      }}
                    >
                      注册
                    </Button>
                  </>
                )}
              >
                <Suspense fallback={<Spinner />}>
                  <Menu>
                    <MenuTrigger
                      as={Avatar}
                      width="2.5rem"
                      height="2.5rem"
                      class="cursor-pointer"
                      name={meResource()?.name}
                      src={getAvatar()}
                    />
                    <MenuContent>
                      <MenuItem
                        icon={
                          <SolarUserBoldDuotone class="fill-brand-primary/80" />
                        }
                        onSelect={() => {
                          navigate("/admin/photos");
                        }}
                      >
                        个人中心
                      </MenuItem>
                      <MenuItem
                        onSelect={() => {
                          logout()
                            .then((_) => {
                              untrack(() => {
                                notificationService.show({
                                  title: "已退出",
                                  status: "success",
                                });
                                navigate("/service");
                              });
                            })
                            .catch((e) => {
                              notificationService.show({
                                title: "退出失败",
                                status: "danger",
                                description: e.message,
                              });
                            });
                        }}
                        icon={<SolarLogout2BoldDuotone class="fill-red-7/87" />}
                        colorScheme="danger"
                      >
                        退出登录
                      </MenuItem>
                    </MenuContent>
                  </Menu>
                </Suspense>
              </ErrorBoundary>
              <IconButton
                onClick={toggleColorMode}
                aria-label="切换主题"
                class="btn"
                icon={
                  colorMode() === "dark" ? (
                    <MaterialSymbolsLightMode />
                  ) : (
                    <MaterialSymbolsDarkMode />
                  )
                }
              />
            </div>
          </nav>
        </div>
      </header>
      <main
        class="font-sans pt-21"
        classList={{
          dark: colorMode() === "dark",
          light: colorMode() === "light",
          "absolute left-0 right-0 top-0 bottom-0 overflow-hidden flex flex-col":
            props.mode === "admin",
        }}
      >
        <div class="grow-1 shrink-2 relative overflow-hidden">
          {props.children(userResult)}
        </div>
        <footer class="container-compact text-center py-4 shrink-0">
          <h5 class="font-bold text-primary">关注我们</h5>
          <ul class="text-center flex items-center justify-center gap-4 mt-2">
            <BrandItem brand={<TablerBrandWechat />} />
            <BrandItem brand={<TablerBrandBilibili />} />
            <BrandItem brand={<TablerBrandTiktok />} />
            <BrandItem brand={<TablerBrandGithub />} />
          </ul>
          <p class="font-bold text-secondary mt-2">
            Designed by{" "}
            <Anchor target="_blank" href="https://github.com/zacharychin233">
              Haodong Qin
            </Anchor>
          </p>
        </footer>
      </main>

      <Show when={location.pathname.endsWith("login")}>
        {/* <div class="absolute top-0 left-0 bottom-0 right-0 z--1 overflow-hidden">
          <Image src={bg} class="opacity-5" />
        </div> */}
      </Show>
    </>
  );
}
