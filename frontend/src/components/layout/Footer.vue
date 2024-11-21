<script setup lang="ts">
const currentYear = new Date().getFullYear();
</script>

<template>
  <footer class="bg-gradient-to-r bg-dark-color text-white py-12 mt-auto">
    <div class="container mx-auto px-6">
      <div class="grid grid-cols-1 md:grid-cols-3 gap-8">
        <div class="space-y-4">
          <div class="flex items-center space-x-2">
            <img alt="SSAFY TRIP" class="w-8 h-8" />
            <!-- 흰색 로고 이미지 추가 필요 -->
            <h2 class="text-2xl font-bold">SSAFY TRIP</h2>
          </div>
          <p class="text-blue-200">여행의 모든 순간을 함께합니다</p>
        </div>

        <div class="space-y-4">
          <h3 class="text-lg font-semibold">Contact Us</h3>
          <div class="space-y-2 text-blue-200">
            <p>Email: contact@ssafytrip.com</p>
            <p>Tel: 02-1234-5678</p>
          </div>
        </div>

        <div class="space-y-4">
          <h3 class="text-lg font-semibold">Developers</h3>
          <div class="space-y-2 text-blue-200">
            <p>SSAFY 12기</p>
            <p>강희민 김연수</p>
          </div>
        </div>
      </div>

      <div class="mt-8 pt-8 border-t border-main-color">
        <div
          class="flex flex-col md:flex-row justify-between items-center space-y-4 md:space-y-0"
        >
          <p class="text-sm text-blue-200">
            &copy; {{ currentYear }} SSAFY TRIP. All rights reserved.
          </p>
          <div class="flex space-x-6">
            <a href="#" class="text-blue-200 hover:text-white transition-colors"
              >Privacy Policy</a
            >
            <a href="#" class="text-blue-200 hover:text-white transition-colors"
              >Terms of Service</a
            >
          </div>
        </div>
      </div>
    </div>
  </footer>
</template>
