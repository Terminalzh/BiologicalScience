import { Anchor } from "@hope-ui/solid";
import SpeciesCard from "../species/SpeciesCard";
import Title from "./Title";
import pic from "~/assets/images/animal1.webp";
import { For, createResource } from "solid-js";
import { recommendSpecies } from "~/api/species";
import { catchResource } from "~/utils";

export default function Recommends() {
  const [recommendResource] = createResource(recommendSpecies);
  const recommendResult = catchResource(recommendResource, (e) => {});

  return (
    <section class="container-compact">
      <Title title="今日推荐" description="我们为您精心挑选的神奇物种" />
      <div class="grid grid-cols-3 gap-8 mt-8">
        <For each={recommendResult()}>
          {(item) => <SpeciesCard data={item} />}
        </For>
      </div>
      <div id="about-us"></div>

      <div class="py-8 text-end">
        <Anchor href="service/retrieval">探索更多</Anchor>
      </div>
    </section>
  );
}
