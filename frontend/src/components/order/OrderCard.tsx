import { AspectRatio, Image, Tooltip } from "@hope-ui/solid";
import { JSX } from "solid-js";
import { useNavigate } from "solid-start";

export interface OrderCardProps {
  class?: string;
  src: string;
  latinName: string;
  chineseName: string;
  children: JSX.Element;
}

export default function OrderCard(props: OrderCardProps) {
  const navigate = useNavigate();
  return (
    <div
      class={`${props.class} flex gap-6 items-center justify-center bg-brand-primary/10 px-8 py-6 rounded-std dark:hover:bg-brand-primary/15 hover:dark:shadow-white/10 hover:shadow-md transition-all cursor-pointer`}
      onClick={() => {
        navigate("/service/retrieval");
      }}
    >
      <AspectRatio ratio={1} class="w-3/8 flex-1">
        <Image src={props.src} borderRadius="1.8rem" />
      </AspectRatio>
      <div class="w-5/8">
        <Tooltip label={props.latinName}>
          <h4 class="font-extrabold text-primary text-4xl font-playfair italic line-clamp-1">
            {props.latinName}
          </h4>
        </Tooltip>
        <div class="relative h-4 mt--1">
          <div class="inline-block w-10 h-2 bg-brand-primary/80 absolute"></div>
          <h5 class="absolute font-sans font-bold leading-tight text-sm leading-none text-secondary text-body ">
            {props.chineseName}
          </h5>
        </div>
        <p class="font-sans text-primary text-sm mt-2 line-clamp-4">
          {props.children}
        </p>
      </div>
    </div>
  );
}
