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
      title: "用户",
      path: "users",
      icon: "i-solar-users-group-two-rounded-bold-duotone",
    });
    result.push({
      title: "物种",
      path: "species",
      icon: "i-solar-database-bold-duotone",
    });

    result.push({
      title: "物种分类",
      path: "category",
      icon: "i-solar-folder-2-bold-duotone",
    });

    result.push({
      title: "照片墙",
      path: "photos-wall",
      icon: "i-solar-album-bold-duotone",
    });

    result.push({
      title: "物种推荐",
      path: "recommends",
      icon: "i-solar-like-bold-duotone",
    });
    result.push({
      title: "轮播图",
      path: "banners",
      icon: "i-solar-slider-vertical-bold-duotone",
    });

    result.push({
      title: "可视化报表",
      path: "charts",
      icon: "i-solar-pie-chart-2-bold-duotone",
    });

    result.push({
      title: "反馈信息",
      path: "feedback",
      icon: "i-solar-dialog-bold-duotone",
    });
  }

  result.push({
    title: "摄影作品",
    path: "photos",
    icon: "i-solar-camera-minimalistic-bold-duotone",
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
            <div class="flex container-compact gap-8 h-full">
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
