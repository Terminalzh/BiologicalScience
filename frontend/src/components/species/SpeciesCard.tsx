import {
  AspectRatio,
  Button,
  Modal,
  ModalBody,
  ModalContent,
  ModalFooter,
  ModalHeader,
  ModalOverlay,
  Spinner,
  Text,
  Tooltip,
  createDisclosure,
  useColorMode,
} from "@hope-ui/solid";
import { Category } from "./CategoryBadge";
import { SearchResultItem } from "~/api/species";
import Picture from "../Picture";
import {
  ErrorBoundary,
  For,
  Suspense,
  createEffect,
  createMemo,
  createResource,
} from "solid-js";
import { getCategory, getCategoryInfo } from "~/api/category";

export interface SpeciesCardProps {
  data: SearchResultItem;
  size?: "sm";
}

export default function SpeciesCard(props: SpeciesCardProps) {
  const categories = createMemo(() => {
    if (!props.data?.inheritance) {
      return undefined;
    }
    return Object.values(props.data?.inheritance).reverse();
  });

  const { isOpen, onOpen, onClose } = createDisclosure();
  const { colorMode } = useColorMode();

  return (
    <>
      <div
        class="inline-block overflow-hidden relative rounded-std border-2 border-solid light:border-dark/10 dark:border-white/10 hover:shadow-lg transition-all cursor-pointer"
        onClick={onOpen}
      >
        <AspectRatio width="$full" ratio={16 / 8}>
          <Picture value={props.data?.pictureUrl || props.data?.betterUrl} />
        </AspectRatio>
        <div
          class="text-end flex flex-col"
          classList={{
            "px-8 pt-8 pb-6": props.size === undefined,
            "px-4 pt-4 pb-2": props.size === "sm",
          }}
        >
          <Category values={categories()} />
          <div
            classList={{
              "px-4 pb-2": props.size === "sm",
            }}
          >
            <Tooltip withArrow label={props.data?.latinName}>
              <h3
                class="font-bold font-playfair leading-none italic text-primary truncate"
                classList={{
                  "text-4xl mt-4": props.size === undefined,
                  "text-2xl mt-2": props.size === "sm",
                }}
              >
                {props.data?.latinName}
              </h3>
            </Tooltip>
            <h4
              class="font-sans font-bold text-2xl text-secondary"
              classList={{
                "text-2xl": props.size === undefined,
                "text-lg": props.size === "sm",
              }}
            >
              {props.data?.cName}
            </h4>
          </div>
        </div>
        <span class="select-none font-brand absolute bottom--4 left--10 z--1 text-8xl dark:text-brand-primary/5 light:text-brand-primary/10">
          Mammalia
        </span>
      </div>

      <Modal
        scrollBehavior="inside"
        opened={isOpen()}
        onClose={onClose}
        size="2xl"
      >
        <ModalOverlay />
        <ModalContent
          classList={{
            dark: colorMode() === "dark",
            light: colorMode() === "light",
          }}
        >
          <ModalHeader>
            {props.data?.latinName} ({props.data?.cName})
          </ModalHeader>
          <ModalBody class="scrollbar-std">
            <AspectRatio ratio={16 / 10} class="w-full mb-4">
              <Picture
                class="rounded-std"
                value={props.data?.pictureUrl || props.data?.betterUrl}
              ></Picture>
            </AspectRatio>
            <Category values={categories()} />
            <p class="mt-4 text-body text-secondary">
              {props.data?.detailIntroduction}
            </p>
          </ModalBody>
          <ModalFooter>
            <Button class="btn" onClick={onClose}>
              取消
            </Button>
          </ModalFooter>
        </ModalContent>
      </Modal>
    </>
  );
}
