import { AspectRatio, Image } from "@hope-ui/solid";
import CategoryBadge from "./CategoryBadge";

export interface SpeciesCardProps {
  src: string;
  latinName: string;
  chineseName: string;
  size?: "sm";
}

export default function SpeciesCard(props: SpeciesCardProps) {
  return (
    <div class="inline-block overflow-hidden relative rounded-std border-2 border-solid light:border-dark/10 dark:border-white/10 hover:shadow-lg transition-all cursor-pointer">
      <AspectRatio width="$full" ratio={16 / 8}>
        <Image src={props.src} />
      </AspectRatio>
      <div
        class="text-end flex flex-col"
        classList={{
          "px-8 pt-8 pb-6": props.size === undefined,
          "px-4 pt-4 pb-2": props.size === "sm",
        }}
      >
        <div class="text-start flex gap2 overflow-auto scrollbar dark:scrollbar-track-color-white/5 scrollbar-thumb-color-brand-primary/10 scrollbar-rounded scrollbar-w-0.5rem scrollbar-radius-2 scrollbar-track-radius-4 scrollbar-thumb-radius-4 pb-2">
          <CategoryBadge
            type="order"
            latinName="Rodentiaaaaaaaaaaaaa"
            chineseName="啮齿目"
          />
          <CategoryBadge
            type="family"
            latinName="Rodentia"
            chineseName="啮齿目"
          />
          <CategoryBadge
            type="genus"
            latinName="Rodentia"
            chineseName="啮齿目"
          />
          <CategoryBadge
            type="genus"
            latinName="Rodentia"
            chineseName="啮齿目"
          />
        </div>
        <div
          classList={{
            "px-4 pb-2": props.size === "sm",
          }}
        >
          <h3
            class="font-bold font-playfair leading-none italic text-primary"
            classList={{
              "text-4xl mt-4": props.size === undefined,
              "text-2xl mt-2": props.size === "sm",
            }}
          >
            {props.latinName}
          </h3>
          <h4
            class="font-sans font-bold text-2xl text-secondary"
            classList={{
              "text-2xl": props.size === undefined,
              "text-lg": props.size === "sm",
            }}
          >
            {props.chineseName}
          </h4>
        </div>
      </div>
      <span class="font-brand absolute bottom--4 left--10 z--1 text-8xl dark:text-brand-primary/5 light:text-brand-primary/10">
        Mammalia
      </span>
    </div>
  );
}
