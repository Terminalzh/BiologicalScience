import { Anchor } from "@hope-ui/solid";
import SpeciesCard from "../species/SpeciesCard";
import Title from "./Title";
import pic from "~/assets/images/animal1.webp";

export default function Recommends() {
  return (
    <section class="container-compact">
      <Title title="今日推荐" description="我们为您精心挑选的神奇物种" />
      <div class="grid grid-cols-3 gap-8 mt-8">
        <SpeciesCard src={pic} chineseName="南方登鼠" latinName="Rhipidomys" />
        <SpeciesCard src={pic} chineseName="南方登鼠" latinName="Rhipidomys" />
        <SpeciesCard src={pic} chineseName="南方登鼠" latinName="Rhipidomys" />
        <SpeciesCard src={pic} chineseName="南方登鼠" latinName="Rhipidomys" />
        <SpeciesCard src={pic} chineseName="南方登鼠" latinName="Rhipidomys" />
        <SpeciesCard src={pic} chineseName="南方登鼠" latinName="Rhipidomys" />
      </div>
      <div class="py-8 text-end">
        <Anchor>探索更多</Anchor>
      </div>
    </section>
  );
}
