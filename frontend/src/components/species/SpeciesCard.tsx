import { AspectRatio, Image } from "@hope-ui/solid";
import CategoryBadge from "./CategoryBadge";

interface SpeciesCardProps {
  src: string;
  latinName: string;
  chineseName: string;
}

export default function SpeciesCard(props: SpeciesCardProps) {
  return (
    <div class="inline-block overflow-hidden relative rounded-std border-2 border-solid light:border-dark/10 dark:border-white/10 hover:shadow-lg transition-all cursor-pointer">
      <AspectRatio width="$full" ratio={16 / 8}>
        <Image src={props.src} borderTopRadius="$std" />
      </AspectRatio>
      <div class="px-8 text-end pt-8 pb-6">
        <div class="text-start flex gap-2 flex-wrap">
          <CategoryBadge
            type="order"
            latinName="Rodentia"
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
        </div>
        <h3 class="font-bold font-playfair leading-none italic text-primary text-4xl mt-4">
          {props.latinName}
        </h3>
        <h4 class="font-sans font-bold text-2xl text-secondary">
          {props.chineseName}
        </h4>
      </div>
      <span class="font-brand absolute bottom--4 left--10 z--1 text-8xl dark:text-brand-primary/5 light:text-brand-primary/10">
        Mammalia
      </span>
    </div>
  );
}
