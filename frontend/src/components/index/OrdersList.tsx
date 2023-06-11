import { For } from "solid-js";
import OrderCard, { OrderCardProps } from "../order/OrderCard";
import Peramelemorphia from "~/assets/images/Peramelemorphia.webp";
import { Anchor } from "@hope-ui/solid";

const orders: Array<OrderCardProps> = [
  {
    src: "https://images.pexels.com/photos/2976950/pexels-photo-2976950.jpeg?auto=compress&cs=tinysrgb&w=600",
    latinName: "Primates",
    chineseName: "灵长目",
    children:
      "灵长目包括人类、猿类（如猩猩、黑猩猩）、猴类等。它们具有高度发达的智力和灵活的手指，适应了树上和陆地上的生活方式。",
  },
  {
    src: "https://images.pexels.com/photos/247373/pexels-photo-247373.jpeg?auto=compress&cs=tinysrgb&w=600",
    latinName: "Lagomorpha",
    chineseName: "兔形目",
    children:
      "兔形目动物是典型的食草动物，以草本植物及树木的嫩枝、嫩叶为食，有的冬季还啃食树皮，一般不喝水。分布于亚洲、欧洲、非洲、北美洲和南美洲的广大地区。",
  },
  {
    src: "https://images.pexels.com/photos/10886167/pexels-photo-10886167.jpeg?auto=compress&cs=tinysrgb&w=600",
    latinName: "Sirenia",
    chineseName: "海牛目",
    children:
      "在海洋哺乳动物中是相当特殊的一群，所属物种均为植食性，以海草与其他水生植物为食。现存共有四种海牛目动物，分为两个科：海牛科的3种海牛与儒艮科的儒艮。",
  },
  {
    src: "https://images.pexels.com/photos/7122719/pexels-photo-7122719.jpeg?auto=compress&cs=tinysrgb&h=566.525&fit=crop&w=633.175&dpr=1",
    latinName: "Proboscidea",
    chineseName: "长鼻目",
    children:
      "只包含一个现存生物的科，象科，即大象。在冰川期有很多现已灭绝的物种， 包括与大象相似的猛犸（长毛象）、乳齿象、恐象、铲齿象、板齿象. 已知的最早的长鼻目动物是Pilgrimella，接下来是始祖象。灵长目包括人类、猿类（如猩猩、黑猩猩）、猴类等。它们具有高度发达的智力和灵活的手指，适应了树上和陆地上的生活方式。",
  },
  {
    src: Peramelemorphia,
    latinName: "Peramelemorphia",
    chineseName: "袋狸目",
    children:
      "接近“杂食性有袋类”的主支。其下所有成员都是澳大利亚及新几内亚的原住民，大部份都有袋狸的体态：肥胖、弓背、尖长的吻、很大的耳朵、幼长的脚及幼的尾巴。它们的体型介乎140克至2公斤，大部份都有约1公斤重。",
  },
  {
    src: "https://images.pexels.com/photos/8439228/pexels-photo-8439228.jpeg?auto=compress&cs=tinysrgb&w=600",
    latinName: "Pilosa",
    chineseName: "披毛目",
    children:
      "现有物种仅分布于美洲。包括了食蚁兽、树懒及已灭绝的地懒。在贫齿总目未被提升为总目而常称作“贫齿目”的时候，披毛目也常称作“披毛亚目”。",
  },
];

export default function OrdersList() {
  return (
    <section class="container-compact mt-6">
      <div class="grid grid-cols-3 gap-4">
        <For each={orders}>{(item) => <OrderCard {...item} />}</For>
      </div>
      <div class="text-end mt-8">
        <Anchor href="service/retrieval">查看更多</Anchor>
      </div>
    </section>
  );
}
