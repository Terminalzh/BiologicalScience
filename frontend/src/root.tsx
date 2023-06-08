// @refresh reload
import {
  Button,
  HopeProvider,
  HopeThemeConfig,
  NotificationsProvider,
} from "@hope-ui/solid";
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
  useLocation,
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
    Button: {
      baseStyle: {
        root: {
          borderRadius: "$xl",
          fontWeight: "bold",
          letterSpacing: "0.2rem",
        },
      },
    },

    Anchor: {
      baseStyle: {
        position: "relative",
        textDecoration: "none",
        fontFamily: "$sans",
        fontWeight: "bold",
        zIndex: "2",
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

    FormControl: {
      baseStyle: {
        label: {
          fontWeight: "bold",
          fontSize: "$md",
          marginBottom: "$2",
        },
      },
    },
    Input: {
      baseStyle: {
        input: {
          border: "none",
          borderRadius: "$xl",
          _light: {
            background: "$blackAlpha3",
          },
          _dark: {
            background: "$whiteAlpha4",
          },
        },
      },
    },

    Textarea: {
      baseStyle: {
        border: "none",
        borderRadius: "$xl",
        _light: {
          background: "$blackAlpha3",
        },
        _dark: {
          background: "$whiteAlpha4",
        },
      },
    },

    Notification: {
      baseStyle: {
        root: {
          borderRadius: "$2xl",
        },
      },
    },

    Menu: {
      baseStyle: {
        content: {
          borderRadius: "$2xl",
          paddingLeft: "$2",
          paddingRight: "$2",
          paddingTop: "$3",
          paddingBottom: "$3",
        },
        item: {
          borderRadius: "$2xl",
        },
      },
    },

    Popover: {
      baseStyle: {
        content: {
          borderRadius: "$2xl",
          padding: "1rem",
        },
      },
    },

    Modal: {
      baseStyle: {
        content: {
          borderRadius: "$std",
          padding: "1rem",
        },
      },
    },

    Select: {
      baseStyle: {
        content: {
          borderRadius: "$2xl",
        },
        option: {
          borderRadius: "$xl",
        },
        trigger: {
          borderRadius: "$xl",
          border: "none",
          _light: {
            background: "$blackAlpha3",
          },
          _dark: {
            background: "$whiteAlpha4",
          },
        },
      },
    },
  },
};

export default function Root() {
  return (
    <HopeProvider config={config}>
      <NotificationsProvider>
        <Html lang="en" class="h-full">
          <Head>
            <Title>Mammalia | 哺乳纲动物管理系统</Title>
            <Meta charset="utf-8" />
            <Meta
              name="viewport"
              content="width=device-width, initial-scale=1"
            />
          </Head>
          <Body class="transition-all h-full">
            <Routes>
              <FileRoutes />
            </Routes>
            <Scripts />
          </Body>
        </Html>
      </NotificationsProvider>
    </HopeProvider>
  );
}
