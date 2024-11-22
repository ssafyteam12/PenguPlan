<script setup>
import { onMounted, ref } from "vue";
import { Button } from "@/components/ui/button";

const props = defineProps({
  activeSection: {
    type: String,
    required: true,
  },
});

const emit = defineEmits(["scroll-section"]);

const scrollToContent = () => {
  const content = document.getElementById("info-id");
  content?.scrollIntoView({ behavior: "smooth" });
  emit("scroll-section", "info");
};

const heroText = ref("");
const completeText = "당신만의 특별한 여행을 시작하세요";
let currentIndex = 0;

onMounted(() => {
  const typeText = () => {
    if (currentIndex < completeText.length) {
      heroText.value += completeText[currentIndex];
      currentIndex++;
      setTimeout(typeText, 100);
    }
  };
  typeText();
});
</script>

<template>
  <div class="min-h-screen relative overflow-hidden">
    <div
      class="absolute inset-0 bg-gradient-to-b from-sky-100 via-blue-50 to-white"
    ></div>

    <div class="absolute inset-0">
      <svg
        width="100%"
        height="100%"
        viewBox="0 0 1000 600"
        preserveAspectRatio="xMidYMid slice"
      >
        <g class="clouds">
          <path
            class="cloud cloud1"
            d="M50,100 A30,30 0 0,1 110,100 A20,20 0 0,1 130,120 L30,120 A20,20 0 0,1 50,100"
            fill="white"
          />
          <path
            class="cloud cloud2"
            d="M250,150 A30,30 0 0,1 310,150 A20,20 0 0,1 330,170 L230,170 A20,20 0 0,1 250,150"
            fill="white"
          />
          <path
            class="cloud cloud3"
            d="M450,80 A30,30 0 0,1 510,80 A20,20 0 0,1 530,100 L430,100 A20,20 0 0,1 450,80"
            fill="white"
          />
        </g>

        <!-- Korea landmarks silhouettes -->
        <g class="landmarks" fill="#E2E8F0">
          <path
            d="M100,400 L150,300 L200,400 L180,400 L180,450 L120,450 L120,400 Z"
          />
          <path d="M300,450 Q350,350 400,450 Z" />
          <path d="M500,450 L550,380 L600,450 Z" />
          <path d="M700,450 C700,400 800,400 800,450 Z" />
        </g>

        <!-- Improved Penguin -->
        <g class="penguin">
          <!-- Body -->
          <ellipse cx="100" cy="160" rx="50" ry="60" fill="#2C3E50">
            <animate
              attributeName="ry"
              values="60;58;60"
              dur="1s"
              repeatCount="indefinite"
            />
          </ellipse>
          <ellipse cx="100" cy="160" rx="35" ry="45" fill="white">
            <animate
              attributeName="ry"
              values="45;43;45"
              dur="1s"
              repeatCount="indefinite"
            />
          </ellipse>

          <!-- Head -->
          <g class="head">
            <circle cx="100" cy="80" r="40" fill="#2C3E50" />
            <ellipse cx="100" cy="90" rx="25" ry="20" fill="white" />
            <circle cx="85" cy="70" r="5" fill="black" />
            <circle cx="115" cy="70" r="5" fill="black" />
            <path d="M95,85 L105,85 L100,95 Z" fill="#FF9800" />
          </g>

          <!-- Suitcase -->
          <rect x="150" y="150" width="40" height="30" fill="#E74C3C" />
          <rect x="165" y="140" width="10" height="10" fill="#C0392B" />
        </g>
      </svg>
    </div>

    <!-- Main content -->
    <div
      class="relative z-10 flex flex-col items-center justify-center min-h-screen px-4"
    >
      <div class="text-center mb-12">
        <h1 class="text-4xl md:text-6xl font-bold text-gray-800 mb-6">
          <span class="typing-text">{{ heroText }}</span>
          <span class="cursor">|</span>
        </h1>
        <p class="text-xl md:text-2xl text-gray-600 max-w-2xl mx-auto mt-4">
          PENGUTRIP과 함께하는 스마트한 여행 계획
        </p>
      </div>

      <!-- Navigation buttons -->
      <div class="flex items-center gap-4 mb-8">
        <router-link to="/trips">
          <Button
            variant="ghost"
            class="text-lg font-medium text-gray-700 hover:text-blue-600 hover:bg-blue-50 transition-all duration-300"
          >
            둘러보기
          </Button>
        </router-link>
        <div class="text-gray-300 text-2xl">|</div>
        <router-link to="/plan">
          <Button
            variant="ghost"
            class="text-lg font-medium text-gray-700 hover:text-blue-600 hover:bg-blue-50 transition-all duration-300"
          >
            여행하기
          </Button>
        </router-link>
      </div>

      <!-- CTA Button -->
      <div class="mt-4 animate-bounce">
        <Button
          @click="scrollToContent"
          class="px-8 py-4 text-lg bg-blue-600 hover:bg-blue-700 text-white rounded-full shadow-lg transform transition-all duration-300 hover:scale-105"
        >
          여행 시작하기
          <span class="ml-2">↓</span>
        </Button>
      </div>
    </div>
  </div>
</template>

<style scoped>
.typing-text {
  @apply inline-block;
}

.cursor {
  @apply inline-block animate-pulse;
}

.clouds path {
  animation: float 20s infinite linear;
  opacity: 0.8;
}

.cloud1 {
  animation-delay: 0s;
}
.cloud2 {
  animation-delay: -7s;
}
.cloud3 {
  animation-delay: -14s;
}

.penguin {
  animation: waddle 30s infinite linear;
}

.head {
  animation: bobHead 2s infinite ease-in-out;
}

.wing {
  animation: flapWing 1s infinite ease-in-out alternate;
}

.wing.right {
  animation-delay: 0.5s;
}

.foot {
  transform-origin: top;
}

@keyframes float {
  from {
    transform: translateX(-100px);
  }
  to {
    transform: translateX(1100px);
  }
}

@keyframes waddle {
  0% {
    transform: translate(-100px, 380px) scale(0.5) rotate(0deg) translateY(0);
  }
  15% {
    transform: translate(175px, 380px) scale(0.5) rotate(3deg) translateY(-5px);
  }
  25% {
    transform: translate(275px, 380px) scale(0.5) rotate(-3deg) translateY(0);
  }
  40% {
    transform: translate(400px, 380px) scale(0.5) rotate(3deg) translateY(-5px);
  }
  50% {
    transform: translate(500px, 380px) scale(0.5) rotate(-3deg) translateY(0);
  }
  65% {
    transform: translate(675px, 380px) scale(0.5) rotate(3deg) translateY(-5px);
  }
  75% {
    transform: translate(825px, 380px) scale(0.5) rotate(-3deg) translateY(0);
  }
  90% {
    transform: translate(950px, 380px) scale(0.5) rotate(3deg) translateY(-5px);
  }
  100% {
    transform: translate(1100px, 380px) scale(0.5) rotate(0deg) translateY(0);
  }
}

@keyframes bobHead {
  0%,
  100% {
    transform: translateY(0) rotate(0deg);
  }
  25% {
    transform: translateY(2px) rotate(-2deg);
  }
  75% {
    transform: translateY(2px) rotate(2deg);
  }
}

/* Responsive adjustments */
@media (max-width: 768px) {
  .penguin {
    transform: scale(0.3);
  }
}
</style>
