import { AspectRatio, Image } from "@hope-ui/solid";
import Title from "./Title";
import pic from "~/assets/images/lion.jpg";

const PhotoItem = (props: { src: string }) => {
  return (
    <AspectRatio ratio={16 / 10} w="15rem" class="m-4">
      <Image src={props.src} shadow="$lg" borderRadius="$std" />
    </AspectRatio>
  );
};

// 只能为偶数个图片
const Line = (props: { fromLeft?: boolean }) => {
  return (
    <div class="relative overflow-hidden z-1 w-full h-[11.375rem] line-container">
      <div class="inline-block absolute h-full z-2 left-0 bg-gradient-to-r light:from-white dark:from-brand-background to-transparent w-40"></div>
      <div class="inline-block absolute h-full z-2 right-0 bg-gradient-to-l light:from-white dark:from-brand-background to-transparent w-40"></div>
      <ul
        class="list-none absolute top-0 left-0 overflow-hidden flex line-ul px-2"
        style={{
          animation: `${
            props.fromLeft ? "scrollLeft2Right" : "scrollRight2Left"
          } 30s infinite linear`,
        }}
      >
        <PhotoItem src={pic} />
        <PhotoItem src={pic} />
        <PhotoItem src={pic} />
        <PhotoItem src={pic} />
        <PhotoItem src={pic} />
        <PhotoItem src={pic} />
        <PhotoItem src={pic} />
        <PhotoItem src={pic} />
        <PhotoItem src={pic} />
        <PhotoItem src={pic} />
      </ul>
    </div>
  );
};

export default function PhotosWall() {
  return (
    <section class="container-compact py-4">
      <Title title="热门动物" description="镜头下的哺乳动物"></Title>
      <Line fromLeft />
      <Line />
    </section>
  );
}
