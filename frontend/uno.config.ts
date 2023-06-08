import { defineConfig, presetIcons, presetUno, presetWebFonts } from "unocss";
import { presetScrollbar } from "unocss-preset-scrollbar";

export default defineConfig({
  theme: {
    colors: {
      brand: {
        primary: "#FAB752",
        accent: "#18DF07",
        background: "#151718",
      },
    },

    fontFamily: {
      slogan: "SmileySans",
    },
  },
  shortcuts: {
    btn: "font-bold tracking-1 bg-brand-primary/87 rounded-3",
    "btn-outlined":
      "font-bold tracking-1 bg-brand-primary/20 rounded-3 border-solid border-2.5 border-brand-primary/87 text-brand-primary hover:bg-brand-primary/40",

    "container-compact":
      "m-auto sm:w-full px-2 sm:max-w-[640px] md:max-w-[768px] lg:max-w-[1024px] 2xl:max-w-[1180px]",
    "text-primary": "light:text-dark/87 dark:text-white/87",
    "text-secondary": "light:text-dark/53 dark:text-white/53",
    "text-body": "text-lg",
    "rounded-std": "rounded-[2.5rem]",
    anchor: "font-bold font-sans text-primary border-b-2 border-b-solid",
    "scrollbar-std":
      "scrollbar dark:scrollbar-track-color-white/5 scrollbar-thumb-color-brand-primary/10 scrollbar-rounded scrollbar-w-0.5rem scrollbar-radius-2 scrollbar-track-radius-4 scrollbar-thumb-radius-4 ",
  },
  presets: [
    presetUno(),
    presetWebFonts({
      provider: "google",
      fonts: {
        brand: "Righteous",
        playfair: "Playfair",
      },
    }),
    presetIcons(),
    presetScrollbar({}),
  ],
});
