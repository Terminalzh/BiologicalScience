import LandingPage from "~/components/index/LandingPage";
import OrdersList from "~/components/index/OrdersList";
import PhotosWall from "~/components/index/PhotosWall";

export default function Service() {
  return (
    <div class="flex flex-col">
      <LandingPage />
      <OrdersList />
      <section class="bg-brand-primary/80 py-8 mt-12">
        <h2 class="container-compact text-black/80 font-sans font-bold text-4xl text-center tracking-widest">
          探索自然的奥秘！
        </h2>
      </section>
      <PhotosWall />
    </div>
  );
}
