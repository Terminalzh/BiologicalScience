import { defineConfig, presetUno, presetWebFonts } from "unocss";

export default defineConfig({
  theme: {
    colors: {
      brand: {
        primary: "#FAB752",
        accent: "#18DF07",
      },
    },
  },
  shortcuts: {
    btn: "font-bold tracking-1 bg-brand-primary/87 rounded-3",
    "btn-outlined":
      "font-bold tracking-1 bg-brand-primary/20 rounded-3 border-solid border-2.5 border-brand-primary/87 text-brand-primary hover:bg-brand-primary/40",

    "container-compact":
      "m-auto sm:w-full px-2 sm:max-w-[640px] md:max-w-[768px] lg:max-w-[1024px] 2xl:max-w-[1180px]",
  },
  presets: [
    presetUno(),
    presetWebFonts({
      provider: "google",
      fonts: {
        brand: "Righteous",
      },
    }),
  ],
});
