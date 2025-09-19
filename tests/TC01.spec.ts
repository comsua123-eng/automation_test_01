import { test, expect } from '@playwright/test';

// Update with your actual credentials
const validUsername = 'testuser';
const validPassword = 'password123';

test('Successful login with valid credentials', async ({ page }) => {
  // Navigate to the Shopee login page
  await page.goto('https://shopee.vn/buyer/login');

  // Enter valid username
  await page.fill('#login-username', validUsername);

  // Enter valid password
  await page.fill('#login-password', validPassword);

  // Click the 'Login' button
  await page.click('button:has-text("Đăng nhập")'); // Update the selector if needed

  // Assertion: Check for successful login (e.g., presence of a specific element on the home page)
  // This assertion needs to be adapted based on the actual UI after successful login
  await expect(page.locator('.some-element-on-homepage')).toBeVisible();
});