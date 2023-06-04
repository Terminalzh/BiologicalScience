import { AspectRatio, Image } from "@hope-ui/solid";
import { JSX } from "solid-js";
import cwca from "~/assets/images/cwca.png";

const AchievementItem = (props: { key: string; children: JSX.Element }) => {
  return (
    <div>
      <p class="text-secondary font-bold">{props.key}</p>
      <p class="text-primary text-4xl font-brand font-bold">{props.children}</p>
    </div>
  );
};

export default function AboutUs() {
  return (
    <section class="bg-brand-primary/80 py-8 items-center justify-center">
      <div class="flex container-compact  gap-32 items-center">
        <div class="flex flex-col gap-5">
          <p class="font-sans text-xl font-bold light:text-white/87 dark:text-black/87">
            我们所努力的方向
          </p>
          <h2 class="font-bold text-5xl font-sans tracking-widest text-primary leading-tight">
            保护野生动物，共建美丽中国
          </h2>
          <p class="text-secondary text-body text-sans">
            中国野生动物保护协会于1983年12月22日经国务院批准在北京成立，是中国科协所属全国性社会团体。
          </p>
          <div class="flex gap-8">
            <AchievementItem key="会员数量">
              410<span class="mx-1">K+</span>
            </AchievementItem>
            <AchievementItem key="始于">1983</AchievementItem>
            <AchievementItem key="省、地、市、县级协会">773</AchievementItem>
          </div>
        </div>
        <Image class="h-14rem" src={cwca} />
      </div>
    </section>
  );
}
