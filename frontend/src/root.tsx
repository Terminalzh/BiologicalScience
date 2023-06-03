// @refresh reload
import { Button, HopeProvider, HopeThemeConfig } from "@hope-ui/solid";
import { Suspense } from "solid-js";
import {
  A,
  Body,
  ErrorBoundary,
  FileRoutes,
  Head,
  Html,
  Meta,
  Routes,
  Scripts,
  Title,
} from "solid-start";
import "virtual:uno.css";
import "./style.css";

const config: HopeThemeConfig = {
  initialColorMode: "system",
  lightTheme: {
    radii: {
      std: "2.5rem",
    },

    colors: {
      primary1: "#FEF5E6",
      primary2: "#FDEACE",
      primary3: "#FDE0B5",
      primary4: "#FCD69C",
      primary5: "#FBCB83",
      primary6: "#FAC16B",
      primary7: "#FAB752",
      primary8: "#FFAE33",
      primary9: "#F8A220",
      primary10: "#DF8907",
      primary11: "#AD6A05",
      primary12: "#633D03",

      accent1: "#E8FEE6",
      accent2: "#D1FDCE",
      accent3: "#A4FC9C",
      accent4: "#8DFB83",
      accent5: "#76FA6B",
      accent6: "#5FFA52",
      accent7: "#5FFA52",
      accent8: "#48F939",
      accent9: "#32F820",
      accent10: "#18DF07",
      accent11: "#13AD05",
      accent12: "#0B6303",
    },
  },

  darkTheme: {
    radii: {
      std: "2.5rem",
    },

    colors: {
      primary1: "#FEF5E6",
      primary2: "#FDEACE",
      primary3: "#FDE0B5",
      primary4: "#FCD69C",
      primary5: "#FBCB83",
      primary6: "#FAC16B",
      primary7: "#FAB752",
      primary8: "#FFAE33",
      primary9: "#F8A220",
      primary10: "#DF8907",
      primary11: "#AD6A05",
      primary12: "#633D03",

      accent1: "#E8FEE6",
      accent2: "#D1FDCE",
      accent3: "#A4FC9C",
      accent4: "#8DFB83",
      accent5: "#76FA6B",
      accent6: "#5FFA52",
      accent7: "#5FFA52",
      accent8: "#48F939",
      accent9: "#32F820",
      accent10: "#18DF07",
      accent11: "#13AD05",
      accent12: "#0B6303",
    },
  },

  components: {
    Anchor: {
      baseStyle: {
        position: "relative",
        textDecoration: "none",
        fontFamily: "$sans",
        fontWeight: "bold",
        padding: "0.2rem 0.5rem",
        transition: "all 300ms",

        _after: {
          content: "",
          position: "absolute",
          bottom: "0",
          zIndex: "-1",
          left: "0",
          right: "0",
          height: "0.2rem",
          borderRadius: "$sm",

          background: "$primary3",
          transition: "all 100ms",
        },

        _hover: {
          color: "Black",
          _after: {
            height: "100%",
          },
        },
      },
    },
  },
};

export default function Root() {
  return (
    <HopeProvider config={config}>
      <Html lang="en">
        <Head>
          <Title>SolidStart - Bare</Title>
          <Meta charset="utf-8" />
          <Meta name="viewport" content="width=device-width, initial-scale=1" />
        </Head>
        <Body class="transition-all pt-20">
          <Routes>
            <FileRoutes />
          </Routes>
          <Scripts />
        </Body>
      </Html>
    </HopeProvider>
  );
}
