import { AspectRatio, Image } from "@hope-ui/solid";
import { JSX } from "solid-js";

export interface OrderCardProps {
  class?: string;
  src: string;
  latinName: string;
  chineseName: string;
  children: JSX.Element;
}

export default function OrderCard(props: OrderCardProps) {
  return (
    <div
      class={`${props.class} flex gap-6 items-center justify-center bg-brand-primary/10 px-8 py-6 rounded-std dark:hover:bg-brand-primary/15 hover:dark:shadow-white/10 hover:shadow-md transition-all cursor-pointer`}
    >
      <AspectRatio ratio={1} class="w-3/8">
        <Image src={props.src} borderRadius="1.8rem" />
      </AspectRatio>
      <div class="flex-1">
        <h4 class="font-extrabold text-primary text-4xl font-playfair italic">
          {props.latinName}
        </h4>
        <div class="relative h-4 mt--1">
          <div class="inline-block w-10 h-2 bg-amber absolute"></div>
          <h5 class="absolute font-sans font-bold leading-tight text-sm leading-none text-secondary text-body ">
            {props.chineseName}
          </h5>
        </div>
        <p class="font-sans text-primary text-sm mt-2">{props.children}</p>
      </div>
    </div>
  );
}
