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
    <tr>
      <td style="border: 1px solid #dddddd; padding: 8px;"><strong>PLK</strong><br><span style="font-size: 0.8em; color: #555;">(Principle of Least Knowledge)</span></td>
      <td style="border: 1px solid #dddddd; padding: 8px;">ReservationService</td>
      <td style="border: 1px solid #dddddd; padding: 8px; color: red;"><strong>مورد نقض</strong></td>
      <td style="border: 1px solid #dddddd; padding: 8px;">استفاده از زنجیره‌ی متدها (Method Chaining) مانند <code>res.getCustomer().getName()</code> نشان‌دهنده این است که کلاس سرویس بیش از حد از ساختار داخلی کلاس <code>Reservation</code> و اشیای درون آن آگاه است.</td>
    </tr>
    <tr>
      <td style="border: 1px solid #dddddd; padding: 8px;"><strong>CRP</strong><br><span style="font-size: 0.8em; color: #555;">(Composite Reuse Principle)</span></td>
      <td style="border: 1px solid #dddddd; padding: 8px;">ReservationService</td>
      <td style="border: 1px solid #dddddd; padding: 8px; color: green;"><strong>مورد برقراری</strong></td>
      <td style="border: 1px solid #dddddd; padding: 8px;">این کلاس برای دسترسی به متدهای پرداخت، به جای ارث‌بری (Inheritance) از کلاس <code>PaymentProcessor</code>، یک نمونه از آن را به عنوان فیلد (Composition) در خود نگه داشته است که روشی منعطف‌تر است.</td>
    </tr>


  </tbody>
</table>
<hr>
<br>

<div dir="rtl">

<h3>گام سوم: اصلاح موارد نقض (Step 3)</h3>
<p>در این گام، با بازنویسی کد و اعمال الگوهای طراحی (Design Patterns)، تمامی موارد نقض اصول SOLID برطرف شدند. شرح تغییرات برای هر اصل به صورت زیر است:</p>

<table style="direction: rtl; width: 100%;">
  <thead>
    <tr style="background-color: #f2f2f2;">
      <th style="border: 1px solid #dddddd; padding: 8px; width: 15%;">اصل</th>
      <th style="border: 1px solid #dddddd; padding: 8px;">راهکار اعمال شده برای اصلاح</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td style="border: 1px solid #dddddd; padding: 8px;"><strong>SRP</strong></td>
      <td style="border: 1px solid #dddddd; padding: 8px;">
        وظیفه چاپ فاکتور که پیش از این در داخل <code>ReservationService</code> انجام می‌شد، به یک کلاس جداگانه به نام <code>InvoicePrinter</code> منتقل شد. اکنون <code>ReservationService</code> تنها مسئول مدیریت فرآیند رزرو است.
      </td>
    </tr>
    <tr>
      <td style="border: 1px solid #dddddd; padding: 8px;"><strong>OCP</strong></td>
      <td style="border: 1px solid #dddddd; padding: 8px;">
        دستورات شرطی <code>switch-case</code> که برای انتخاب روش پرداخت و روش نوتیفیکیشن وجود داشتند، حذف شدند. به جای آن‌ها از Polymorphism استفاده شد. اکنون برای اضافه کردن یک روش پرداخت جدید، کافیست یک کلاس جدید بسازیم که اینترفیس مربوطه را پیاده‌سازی کند، بدون اینکه نیاز باشد کد کلاس سرویس تغییر کند.
      </td>
    </tr>
    <tr>
      <td style="border: 1px solid #dddddd; padding: 8px;"><strong>LSP</strong></td>
      <td style="border: 1px solid #dddddd; padding: 8px;">
        رفتارهای خاص اتاق‌های لوکس (LuxuryRoom) به گونه‌ای مدیریت شدند که جایگزینی آن‌ها با کلاس والد (Room) باعث ایجاد خطای منطقی یا نیاز به بررسی نوع (Type checking) در کلاس سرویس نشود. رفتارها در سطح انتزاع یکسان‌سازی شدند.
      </td>
    </tr>
    <tr>
      <td style="border: 1px solid #dddddd; padding: 8px;"><strong>ISP</strong></td>
      <td style="border: 1px solid #dddddd; padding: 8px;">
        کلاس بزرگ <code>PaymentProcessor</code> که همه متدهای پرداخت را داشت حذف شد. به جای آن، اینترفیس کوچک و متمرکز <code>PaymentStrategy</code> تعریف شد که تنها یک متد <code>pay</code> دارد. اکنون کلاینت‌ها مجبور نیستند به روش‌های پرداختی که استفاده نمی‌کنند وابسته باشند.
      </td>
    </tr>
    <tr>
      <td style="border: 1px solid #dddddd; padding: 8px;"><strong>DIP</strong></td>
      <td style="border: 1px solid #dddddd; padding: 8px;">
        وابستگی‌های مستقیم (استفاده از <code>new</code>) در داخل <code>ReservationService</code> حذف شدند. اکنون وابستگی‌ها (مانند <code>PaymentStrategy</code> و <code>NotificationService</code>) از طریق **تزریق وابستگی در سازنده (Constructor Injection)** به کلاس پاس داده می‌شوند. کلاس سرویس حالا به انتزاع (Interface) وابسته است نه پیاده‌سازی (Implementation).
      </td>
    </tr>
    <tr>
      <td style="border: 1px solid #dddddd; padding: 8px;"><strong>PLK</strong></td>
      <td style="border: 1px solid #dddddd; padding: 8px;">
        زنجیره‌ی فراخوانی متدها (مثل <code>res.getCustomer().getName()</code>) حذف شد. متدهای کمکی (Delegate Methods) در کلاس <code>Reservation</code> ایجاد شد تا سرویس مستقیماً اطلاعات مورد نیاز را از خود رزرو بگیرد (مثلاً <code>res.getCustomerName()</code>) و از ساختار داخلی آن بی‌خبر باشد.
      </td>
    </tr>
  </tbody>
</table>

</div>

</div>