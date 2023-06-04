interface TitleProps {
  title: string;
  description: string;
  class?: string;
}

export default function Title(props: TitleProps) {
  return (
    <div class={`${props.class} text-center py-4`}>
      <h4 class="text-brand-primary/80 font-bold text-lg">{props.title}</h4>
      <h3 class="text-primary font-extrabold text-4xl tracking-widest">{props.description}</h3>
    </div>
  );
}
