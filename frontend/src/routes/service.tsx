import { Button, IconButton, useColorMode } from "@hope-ui/solid";
import { JSX } from "solid-js";
import { Outlet } from "solid-start";
import { LogoIcon } from "~/components/LogoIcon";

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

const NavItem = (props: any) => {
  return (
    <li class="cursor-pointer hover:text-brand-primary/87 transition-all">
      {props.children}
    </li>
  );
};

export default function ServiceLayout() {
  const { colorMode, toggleColorMode } = useColorMode();
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
          <nav class="inline flex container-compact items-center py-4">
            <div class="flex items-center gap-2">
              <LogoIcon class="text-brand-primary" />
              <span class="flex-1 font-brand text-brand-primary text-2xl">
                Mammalia
              </span>
            </div>
            <ul class="flex font-sans gap-8 flex-1 items-center list-none justify-center font-500 text-lg">
              <NavItem>主页</NavItem>
              <NavItem>物种检索</NavItem>
              <NavItem>每日推荐</NavItem>
              <NavItem>关于我们</NavItem>
            </ul>
            <div class="flex gap-8 flex-1 items-center justify-end">
              <Button class="btn-outlined">登录</Button>
              <Button class="btn">注册</Button>
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
        class="font-sans"
        classList={{
          dark: colorMode() === "dark",
          light: colorMode() === "light",
        }}
      >
        <Outlet />
      </main>
    </>
  );
}
