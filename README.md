<div dir="rtl">

<h3>گام اول: تغییرات ایجاد شده (Step 1)</h3>
<p>تغییرات اعمال شده برای افزودن قابلیت‌های جدید:</p>

<h4>1. تغییرات مربوط به افزودن پیامک (SMS)</h4>
<table style=direction: rtl;>
  <thead>
    <tr style="background-color: #f2f2f2;">
      <th style="border: 1px solid #dddddd; padding: 8px;">ردیف</th>
      <th style="border: 1px solid #dddddd; padding: 8px;">کلاس تغییر یافته</th>
      <th style="border: 1px solid #dddddd; padding: 8px;">توضیحات</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td style="border: 1px solid #dddddd; padding: 8px;">۱</td>
      <td style="border: 1px solid #dddddd; padding: 8px;">SmsSender</td>
      <td style="border: 1px solid #dddddd; padding: 8px;">افزودن کلاس جدید <code>SmsSender</code> برای ارسال پیامک</td>
    </tr>
    <tr>
      <td style="border: 1px solid #dddddd; padding: 8px;">۲</td>
      <td style="border: 1px solid #dddddd; padding: 8px;">ReservationService</td>
      <td style="border: 1px solid #dddddd; padding: 8px;">افزودن <code>case SMS</code> برای استفاده از کلاس جدید پیامک</td>
    </tr>
  </tbody>
</table>

<br>

<hr>
<br>

<h3>گام دوم: تحلیل اصول SOLID (Step 2)</h3>
<p>جدول زیر بررسی رعایت یا نقض اصول شی‌گرایی در کد اولیه است:</p>

<table style= direction: rtl;>
  <thead>
    <tr style="background-color: #f2f2f2;">
      <th style="border: 1px solid #dddddd; padding: 8px; width: 15%;">اصل</th>
      <th style="border: 1px solid #dddddd; padding: 8px; width: 20%;">کلاس مربوطه</th>
      <th style="border: 1px solid #dddddd; padding: 8px; width: 15%;">وضعیت</th>
      <th style="border: 1px solid #dddddd; padding: 8px;">علت نقض یا برقراری</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td style="border: 1px solid #dddddd; padding: 8px;"><strong>SRP</strong><br><span style="font-size: 0.8em; color: #555;">(Single Responsibility)</span></td>
      <td style="border: 1px solid #dddddd; padding: 8px;">ReservationService</td>
      <td style="border: 1px solid #dddddd; padding: 8px; color: red;"><strong>مورد نقض</strong></td>
      <td style="border: 1px solid #dddddd; padding: 8px;">این کلاس وظایف متعددی دارد: مدیریت منطق رزرو، چاپ فاکتور (Invoice)، مدیریت پرداخت و ارسال ایمیل. هر تغییری در این حوزه‌ها باعث تغییر این کلاس می‌شود.</td>
    </tr>
    <tr>
      <td style="border: 1px solid #dddddd; padding: 8px;"><strong>OCP</strong><br><span style="font-size: 0.8em; color: #555;">(Open/Closed)</span></td>
      <td style="border: 1px solid #dddddd; padding: 8px;">ReservationService</td>
      <td style="border: 1px solid #dddddd; padding: 8px; color: red;"><strong>مورد نقض</strong></td>
      <td style="border: 1px solid #dddddd; padding: 8px;">برای افزودن یک روش پرداخت جدید یا روش نوتیفیکیشن جدید، مجبور هستیم کدهای داخل دستورات <code>switch</code> را تغییر دهیم. کد نسبت به تغییر بسته نیست.</td>
    </tr>
    <tr>
      <td style="border: 1px solid #dddddd; padding: 8px;"><strong>LSP</strong><br><span style="font-size: 0.8em; color: #555;">(Liskov Substitution)</span></td>
      <td style="border: 1px solid #dddddd; padding: 8px;">LuxuryRoom</td>
      <td style="border: 1px solid #dddddd; padding: 8px; color: red;"><strong>مورد نقض</strong></td>
      <td style="border: 1px solid #dddddd; padding: 8px;">کلاس <code>LuxuryRoom</code> رفتارهای خاصی (مثل <code>addFreeDinner</code>) دارد که در کلاس والد (Room) نیست و در کد اصلی (ReservationService) به صورت Hard-code و وابسته به نوع خاص استفاده می‌شود.</td>
    </tr>
        <tr>
      <td style="border: 1px solid #dddddd; padding: 8px;"><strong>ISP</strong><br><span style="font-size: 0.8em; color: #555;">(Interface Segregation)</span></td>
      <td style="border: 1px solid #dddddd; padding: 8px;">PaymentProcessor</td>
      <td style="border: 1px solid #dddddd; padding: 8px; color: red;"><strong>مورد نقض</strong></td>
      <td style="border: 1px solid #dddddd; padding: 8px;">کلاس <code>PaymentProcessor</code> همه روش‌های پرداخت (نقد، کارت، آنلاین) را در خود دارد. اگر بخواهیم فقط از یکی استفاده کنیم یا یک روش جدید اضافه کنیم، کلاس وابسته (Service) درگیر متدهای غیرمرتبط می‌شود. در واقع یک رابط بزرگ و "چاق" داریم.</td>
    </tr>
    <tr>
      <td style="border: 1px solid #dddddd; padding: 8px;"><strong>DIP</strong><br><span style="font-size: 0.8em; color: #555;">(Dependency Inversion)</span></td>
      <td style="border: 1px solid #dddddd; padding: 8px;">ReservationService</td>
      <td style="border: 1px solid #dddddd; padding: 8px; color: red;"><strong>مورد نقض</strong></td>
      <td style="border: 1px solid #dddddd; padding: 8px;">در کلاس <code>ReservationService</code>، ماژول‌های سطح پایین (مثل <code>PaymentProcessor</code> و <code>EmailSender</code>) مستقیماً با دستور <code>new</code> ساخته شده‌اند. این یعنی وابستگی به پیاده‌سازی (Implementation) به جای وابستگی به انتزاع (Abstraction).</td>
    </tr>


  </tbody>
</table>

</div>