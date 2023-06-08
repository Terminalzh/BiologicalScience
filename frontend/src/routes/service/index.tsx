import AboutUs from "~/components/index/AboutUs";
import ContactUs from "~/components/index/ContactUs";
import LandingPage from "~/components/index/LandingPage";
import OrdersList from "~/components/index/OrdersList";
import PhotosWall from "~/components/index/PhotosWall";
import Recommends from "~/components/index/Recommends";

export default function Service() {
  return (
    <div class="flex flex-col gap-12 mb-12">
      <LandingPage />
      <OrdersList />
      <section class="bg-brand-primary/80 py-8">
        <h2 class="container-compact text-black/80 font-sans font-bold text-4xl text-center tracking-widest">
          探索自然的奥秘！
        </h2>
      </section>
      <PhotosWall />
      <Recommends />
      <AboutUs />
      <ContactUs />
    </div>
  );
}
