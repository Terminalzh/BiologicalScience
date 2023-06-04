import { createForm } from "@felte/solid";
import {
  Button,
  FormControl,
  FormLabel,
  Image,
  Input,
  Textarea,
} from "@hope-ui/solid";
import qr from "~/assets/images/qrcode.png";

export default function ContactUs() {
  const { form } = createForm({
    onSubmit(values) {
      console.log(values);
    },
    onError(error, context) {
      console.log(error, context);
    },
  });
  return (
    <section class="flex container-compact justify-around">
      <div>
        <form
          id="contact-form"
          ref={form}
          class="flex flex-col gap-4"
          onSubmit={() => {
            console.log("hhh");
          }}
        >
          <div class="flex gap-4">
            <FormControl required>
              <FormLabel>称谓</FormLabel>
              <Input type="text" name="name" placeholder="请输入您的姓名" />
            </FormControl>

            <FormControl required>
              <FormLabel>联系方式</FormLabel>
              <Input
                required
                type="email"
                name="contact"
                placeholder="feedback@mammalia.com"
              />
            </FormControl>
          </div>
          <FormControl required>
            <FormLabel>内容</FormLabel>
            <Textarea
              name="content"
              rows={8}
              placeholder="请填写您想对我们说的话，举报信息等"
            ></Textarea>
          </FormControl>
        </form>
        <div class="text-end mt-4">
          <Button type="submit" form="contact-form" class="btn">
            提交
          </Button>
        </div>
      </div>
      <div>
        <h5 class="font-bold text-2xl text-primary">电话与通讯地址</h5>
        <p class="text-secondary text-body font-sans">北京市和平里东街18号</p>
        <p class="text-secondary text-body font-sans">cwcaweixin@sina.com</p>
        <p class="text-secondary text-body font-sans">010-84238313</p>

        <Image
          src={qr}
          class="w-50 opacity-80 border-solid border-2 rounded-std p-4 light:border-black/10 dark:border-white/10 mt-10"
        />
      </div>
    </section>
  );
}
