/// <reference types="vite/client" />

declare module "*.vue" {
  import { DefineComponent } from "vue";
  const component: DefineComponent<{}, {}, any>;
  export default component;
}

interface ImportMetaEnv {
  readonly VITE_KAKAO_API_KEY: string;
  readonly VITE_SERVER_URL: string;
  // 필요한 다른 환경 변수를 추가하세요.
}

interface ImportMeta {
  readonly env: ImportMetaEnv;
}
