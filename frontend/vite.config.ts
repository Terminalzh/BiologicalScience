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
      // "/api": "http://127.0.0.1:8080",
      "/api": {
        target: "http://127.0.0.1:8080",
        changeOrigin: true,
      },
      // "/api": {
      //   target: "http://127.0.0.1:4523/m1/2818466-0-default",
      //   changeOrigin: true,
      //   rewrite: (path) => path.replace(/^\/api/, ""),
      // },
      // "/api": {
      //   target: "https://cn-sy-dx-1.natfrp.cloud:31150", // The API is running locally via IIS on this port
      //   changeOrigin: true,
      //   secure: false,
      // },
    },
  },
});
