import { Avatar, Spinner } from "@hope-ui/solid";
import {
  ErrorBoundary,
  For,
  JSX,
  Show,
  Suspense,
  createEffect,
  createMemo,
  createResource,
  onMount,
} from "solid-js";
import { getMe } from "~/api/user";
import Highcharts from "highcharts";
import Sunburst from "highcharts/modules/sunburst";
import { allCategories } from "~/api/category";
import { catchResource } from "~/utils";

export function PhGenderFemaleBold(props: JSX.IntrinsicElements["svg"]) {
  return (
    <svg
      xmlns="http://www.w3.org/2000/svg"
      width="1em"
      height="1em"
      viewBox="0 0 256 256"
      {...props}
    >
      <path d="M212 96a84 84 0 1 0-96 83.13V196H88a12 12 0 0 0 0 24h28v20a12 12 0 0 0 24 0v-20h28a12 12 0 0 0 0-24h-28v-16.87A84.12 84.12 0 0 0 212 96ZM68 96a60 60 0 1 1 60 60a60.07 60.07 0 0 1-60-60Z"></path>
    </svg>
  );
}

export function PhGenderMaleBold(props: JSX.IntrinsicElements["svg"]) {
  return (
    <svg
      xmlns="http://www.w3.org/2000/svg"
      width="1em"
      height="1em"
      viewBox="0 0 256 256"
      {...props}
    >
      <path d="M216 28h-48a12 12 0 0 0 0 24h19l-32.72 32.74a84 84 0 1 0 17 17L204 69v19a12 12 0 0 0 24 0V40a12 12 0 0 0-12-12Zm-69.59 166.46a60 60 0 1 1 0-84.87a60.1 60.1 0 0 1 0 84.87Z"></path>
    </svg>
  );
}

const SectionCard = (props: {
  title?: string;
  children: JSX.Element;
  boxClass?: string;
}) => {
  return (
    <section class="light:bg-dark/5 dark:bg-light/5 px-8 pb-6 pt-5 rounded-std">
      <div class={`mt-4 ${props.boxClass || ""}`}>
        <ErrorBoundary
          fallback={(e) => (
            <div class="flex items-center justify-center min-h-6rem">
              <p class="text-body text-secondary italic text-center">
                {e.message}
              </p>
            </div>
          )}
        >
          <Suspense
            fallback={
              <div class="flex items-center justify-center min-h-6rem">
                <Spinner />
              </div>
            }
          >
            {props.children}
          </Suspense>
        </ErrorBoundary>
      </div>
    </section>
  );
};

const UserInfoItem = (props: { icon: string; value: string }) => {
  return (
    <li class="flex gap-4 items-center">
      <span class={`${props.icon} text-secondary text-xl`}></span>
      <p class="text-body text-primary">{props.value}</p>
    </li>
  );
};

const UserInfo = () => {
  const [userinfo] = createResource(getMe);
  const getAvatar = createMemo(() => {
    try {
      if (userinfo()?.avatar) {
        const pictures = JSON.parse(userinfo()!.avatar);
        return pictures.m;
      }
    } catch (e) {}
    return "";
  });

  const getInfoItems = createMemo(() => {
    const result = new Array();
    try {
      result.push({
        icon: "i-solar-phone-bold-duotone",
        value: userinfo()?.phone,
      });
      result.push({
        icon: "i-solar-letter-bold-duotone",
        value: userinfo()?.email,
      });
    } catch (e) {}
    return result;
  });

  return (
    <SectionCard title="个人信息" boxClass="flex gap-8">
      <div class="flex justify-center items-center flex-col gap-4">
        <Avatar size="xl" src={getAvatar()} />
      </div>
      <div>
        <div class="flex gap-4">
          <span
            class="px-4 py-2 rounded-xl font-bold text-sm font-sans flex-1"
            classList={{
              "bg-brand-primary/20 text-brand-primary/87": !userinfo()?.isAdmin,
              "bg-green/20 text-green-7/87": userinfo()?.isAdmin,
            }}
          >
            {userinfo()?.isAdmin ? "管理员" : "普通用户"}
          </span>
          <div class="flex gap-2 items-center justify-center">
            <h3 class="font-bold text-primary text-xl">{userinfo()?.name}</h3>
            <Show
              when={userinfo()?.gender === "男"}
              fallback={<PhGenderFemaleBold class="fill-pink-5/80" />}
            >
              <PhGenderMaleBold class="fill-blue-5/80" />
            </Show>
          </div>
        </div>

        <ul class="list-none mt-4">
          <For each={getInfoItems()}>
            {(item) => <UserInfoItem {...item} />}
          </For>
        </ul>
      </div>
    </SectionCard>
  );
};

export default function ChartsPage() {
  let chart: Highcharts.Chart;
  onMount(() => {
    Sunburst(Highcharts);

    chart = Highcharts.chart({
      chart: {
        renderTo: "container",
        spacingBottom: 30,
        marginRight: 120,
        backgroundColor: "transparent",
      },
      plotOptions: {
        series: {
          turboThreshold: 2000,
          colors: [
            "#F1C376",
            "#F7E6C4",
            "#FFF4F4",
            "#606C5D",
            "#F97B22",
            "#3A8891",
          ],
        },
        sunburst: {
          dataLabels: {
            style: {
              fontSize: "1rem",
              fontFamily: "Playfair",
              fontStyle: "italic",
            },
          },
          levels: [
            {
              level: 1,
              color: "#F1C376",
              dataLabels: {
                style: {
                  color: "#F1C376",
                },
              },
            },
            {
              level: 2,
              color: "#00FF00",
              dataLabels: {
                style: {
                  color: "#FFFFFF",
                },
              },
            },
            {
              level: 3,
              color: "#0000FF",
              dataLabels: {
                style: {
                  color: "#FFFFFF",
                },
              },
            },
          ],
        },
      },
      title: {
        text: "",
      },
      series: [
        {
          type: "sunburst",
          name: "Root",
          allowDrillToNode: true,
          borderRadius: 3,
          cursor: "pointer",
          dataLabels: {
            format: "{point.name}",
            filter: {
              property: "innerArcLength",
              operator: ">",
              value: 16,
            },
          },
          levels: [
            {
              level: 1,
              levelIsConstant: false,
              dataLabels: {
                filter: {
                  property: "outerArcLength",
                  operator: ">",
                  value: 64,
                },
              },
            },
            {
              level: 2,
              colorByPoint: true,
              colorVariation: {
                key: "brightness",
                to: -0.5,
              },
            },
            {
              level: 3,
              colorVariation: {
                key: "brightness",
                to: -0.5,
              },
            },
            {
              level: 4,
              colorVariation: {
                key: "brightness",
                to: 0.5,
              },
            },
          ],
        },
      ],
    } as any);
  });

  const [categories] = createResource(4, allCategories);
  const categoryResult = catchResource(categories, (e) => {});

  createEffect(() => {
    if (categoryResult()) {
      chart.series[0].setData(categoryResult()! as any);
    }
  });
  return (
    <div>
      <div class="grid grid-cols-2">
        <UserInfo />
      </div>
      <div class="mt-8">
        <Show when={categories.loading}>
          <div class="flex items-center justify-center min-h-20-vh">
            <Spinner />
          </div>
        </Show>
        <figure class="h-60vh opacity-80" id="container"></figure>
      </div>
    </div>
  );
}
