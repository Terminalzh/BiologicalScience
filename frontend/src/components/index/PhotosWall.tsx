import { AspectRatio } from "@hope-ui/solid";
import Title from "./Title";

const PhotoItem = () => {
  return <AspectRatio></AspectRatio>;
};

export default function PhotosWall() {
  return (
    <section class="container-compact">
      <Title title="热门动物" description="镜头下的哺乳动物"></Title>
    </section>
  );
}
