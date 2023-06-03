import { For } from "solid-js";
import OrderCard, { OrderCardProps } from "../order/OrderCard";
import pic from "~/assets/images/animal.jpg";
import { Anchor } from "@hope-ui/solid";

const orders: Array<OrderCardProps> = [
  {
    src: pic,
    latinName: "Primates",
    chineseName: "灵长目",
    children:
      "灵长目包括人类、猿类（如猩猩、黑猩猩）、猴类等。它们具有高度发达的智力和灵活的手指，适应了树上和陆地上的生活方式。",
  },
  {
    src: pic,
    latinName: "Primates",
    chineseName: "灵长目",
    children:
      "灵长目包括人类、猿类（如猩猩、黑猩猩）、猴类等。它们具有高度发达的智力和灵活的手指，适应了树上和陆地上的生活方式。",
  },
  {
    src: pic,
    latinName: "Primates",
    chineseName: "灵长目",
    children:
      "灵长目包括人类、猿类（如猩猩、黑猩猩）、猴类等。它们具有高度发达的智力和灵活的手指，适应了树上和陆地上的生活方式。",
  },
  {
    src: pic,
    latinName: "Primates",
    chineseName: "灵长目",
    children:
      "灵长目包括人类、猿类（如猩猩、黑猩猩）、猴类等。它们具有高度发达的智力和灵活的手指，适应了树上和陆地上的生活方式。",
  },
  {
    src: pic,
    latinName: "Primates",
    chineseName: "灵长目",
    children:
      "灵长目包括人类、猿类（如猩猩、黑猩猩）、猴类等。它们具有高度发达的智力和灵活的手指，适应了树上和陆地上的生活方式。",
  },
  {
    src: pic,
    latinName: "Primates",
    chineseName: "灵长目",
    children:
      "灵长目包括人类、猿类（如猩猩、黑猩猩）、猴类等。它们具有高度发达的智力和灵活的手指，适应了树上和陆地上的生活方式。",
  },
];

export default function OrdersList() {
  return (
    <section class="container-compact">
      <div class="grid grid-cols-3 gap-4">
        <For each={orders}>{(item) => <OrderCard {...item} />}</For>
      </div>
      <div class="text-end mt-8">
        <Anchor href="https://google.com">查看更多</Anchor>
      </div>
    </section>
  );
}
