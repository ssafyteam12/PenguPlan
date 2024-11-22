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
          <!-- Namsan Tower -->
          <path d="M300,450 Q350,350 400,450 Z" />
          <!-- Bukhansan -->
          <path d="M500,450 L550,380 L600,450 Z" />
          <!-- Hallasan -->
          <path d="M700,450 C700,400 800,400 800,450 Z" />
          <!-- Wave for Haeundae -->
        </g>

        <!-- Animated Penguin -->
        <g class="penguin" transform="translate(0, 380) scale(0.5)">
          <!-- Body -->
          <ellipse cx="100" cy="160" rx="50" ry="60" fill="#2C3E50" />
          <ellipse cx="100" cy="160" rx="35" ry="45" fill="white" />

          <!-- Head -->
          <circle cx="100" cy="80" r="40" fill="#2C3E50" />
          <ellipse cx="100" cy="90" rx="25" ry="20" fill="white" />

          <!-- Eyes -->
          <circle cx="85" cy="70" r="5" fill="black" />
          <circle cx="115" cy="70" r="5" fill="black" />

          <!-- Beak -->
          <path d="M95,85 L105,85 L100,95 Z" fill="#FF9800" />

          <!-- Feet -->
          <path
            class="foot left"
            d="M70,220 L85,220 L77.5,235 Z"
            fill="#FF9800"
          />
          <path
            class="foot right"
            d="M115,220 L130,220 L122.5,235 Z"
            fill="#FF9800"
          />

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
      <!-- Text container -->
      <div class="text-center mb-12">
        <h1 class="text-4xl md:text-6xl font-bold text-gray-800 mb-6">
          <span class="typing-text">{{ heroText }}</span>
          <span class="cursor">|</span>
        </h1>
        <p class="text-xl md:text-2xl text-gray-600 max-w-2xl mx-auto mt-4">
          SSAFY TRIP과 함께하는 스마트한 여행 계획
        </p>
      </div>

      <!-- CTA Button -->
      <div class="mt-8 animate-bounce">
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

.foot {
  transform-origin: top;
}

.left {
  animation: walk 1s infinite alternate;
}

.right {
  animation: walk 1s infinite alternate-reverse;
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
  from {
    transform: translate(-100px, 380px) scale(0.5);
  }
  to {
    transform: translate(1100px, 380px) scale(0.5);
  }
}

@keyframes walk {
  from {
    transform: rotate(-10deg);
  }
  to {
    transform: rotate(10deg);
  }
}

/* Responsive adjustments */
@media (max-width: 768px) {
  .penguin {
    transform: scale(0.3);
  }
}
</style>
