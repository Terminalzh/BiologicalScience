import solid from "solid-start/vite";
import { defineConfig } from "vite";
import UnoCSS from "unocss/vite";
import mkcert from "vite-plugin-mkcert";

export default defineConfig({
  plugins: [
    solid({ ssr: false }),
    UnoCSS({
      configFile: "../uno.config.ts",
    }),
    mkcert(),
  ],
  server: {
    https: true,
    proxy: {
      "/api/pictures": "http://127.0.0.1:7711",
      "/api": {
        target: "http://127.0.0.1:4523/m1/2818466-0-default",
        changeOrigin: true,
        rewrite: (path) => path.replace(/^\/api/, ""),
      },
      // "/api": {
      //   target: "https://frp-fan.top:15798", // The API is running locally via IIS on this port
      //   changeOrigin: true,
      //   secure: false,
      // },
    },
  },
});
