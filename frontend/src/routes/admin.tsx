import { For, JSX, createMemo } from "solid-js";
import { Outlet, useLocation, useNavigate } from "solid-start";
import BaseLayout from "~/components/BaseLayout";

interface MenuItemProps {
  title: string;
  path: string;
  icon: string;
}

const getMenu = (isAdmin: boolean) => {
  const result = new Array<MenuItemProps>();
  if (true) {
    result.push({
      title: "用户管理",
      path: "users",
      icon: "i-ph-users-fill",
    });
    result.push({
      title: "照片墙管理",
      path: "photos-wall",
      icon: "i-ph-images-fill",
    });
    result.push({
      title: "物种推荐管理",
      path: "recommends",
      icon: "i-ph-dog-fill",
    });
    result.push({
      title: "轮播图管理",
      path: "banners",
      icon: "i-ph-slideshow-fill",
    });

    result.push({
      title: "可视化报表",
      path: "charts",
      icon: "i-ph-chart-bar-fill",
    });

    result.push({
      title: "举报信息管理",
      path: "feedback",
      icon: "i-ph-chat-circle-dots-fill",
    });
  }

  result.push({
    title: "摄影作品管理",
    path: "photos",
    icon: "i-ph-camera-fill",
  });

  return result;
};

const MenuItem = (props: MenuItemProps) => {
  const navigate = useNavigate();
  const location = useLocation();
  const isSelected = createMemo(() => location.pathname.endsWith(props.path));
  return (
    <p
      class="px-6 py-3 tracking-widest flex items-center gap-4 rounded-full font-bold font-sans cursor-pointer transition-colors"
      classList={{
        "bg-brand-primary/10 light:hover:bg-brand-primary/20 dark:hover:bg-brand-primary/10 text-brand-primary/87":
          isSelected(),
        "light:hover:bg-dark/5 text-primary  dark:hover:bg-white/5":
          !isSelected(),
      }}
      onClick={() => {
        navigate(props.path);
      }}
    >
      <span
        class={`${props.icon} w-1.5rem h-1.5rem transition-all`}
        classList={{
          "text-secondary": !isSelected(),
          "text-brand-primary/87": isSelected(),
        }}
      ></span>

      {props.title}
    </p>
  );
};

export default function AdminLayout() {
  return (
    <BaseLayout mode="admin">
      {(user) => {
        if (user()) {
          return (
            <div class="flex container-compact gap-16 h-full">
              <aside class="">
                <ul class="sticky top-0 list-none dark:bg-white/5 light:bg-dark/5 px-6 py-6 rounded-std min-h-[10rem]">
                  <For each={getMenu(user()!.isAdmin)}>
                    {(item) => (
                      <li>
                        <MenuItem {...item} />
                      </li>
                    )}
                  </For>
                </ul>
              </aside>
              <main class="h-full overflow-auto flex-1 scrollbar dark:scrollbar-track-color-white/5 scrollbar-thumb-color-brand-primary/10 scrollbar-rounded scrollbar-w-0.5rem scrollbar-radius-2 scrollbar-track-radius-4 scrollbar-thumb-radius-4">
                <Outlet />
              </main>
            </div>
          );
        } else {
          return <></>;
        }
      }}
    </BaseLayout>
  );
}
