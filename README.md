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
<h4>2. تغییرات مربوط به افزودن روش پرداخت (On-Site Payment)</h4>
<table style="border-collapse: collapse; width: 100%; text-align: right; direction: rtl;">
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
      <td style="border: 1px solid #dddddd; padding: 8px;">PaymentProcessor</td>
      <td style="border: 1px solid #dddddd; padding: 8px;">افزودن تابع <code>payOnSite</code> برای پرداخت حضوری</td>
    </tr>
    <tr>
      <td style="border: 1px solid #dddddd; padding: 8px;">۲</td>
      <td style="border: 1px solid #dddddd; padding: 8px;">ReservationService</td>
      <td style="border: 1px solid #dddddd; padding: 8px;">افزودن <code>case ON_SITE</code> به متد <code>makeReservation</code></td>
    </tr>
  </tbody>
</table>


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


<h3>گام چهارم: ارزیابی (Step 4)</h3>
<p>
    با بررسی ساختار جدید پروژه (نسخه اصلاح شده با اصول OOD)، پاسخ به سوالات ارزیابی به شرح زیر است:
</p>
<p>
    <strong>سوال ۱: در صورتی که اصول شی‌گرایی از ابتدا رعایت می‌شد، چند تا از تغییرات داده شده در گام اول حذف می‌شد؟</strong><br>
    تغییرات زیر که در گام اول (کد بدون اصول SOLID) انجام دادیم، در ساختار جدید <strong>حذف می‌شدند</strong> (نیازی به انجام آن‌ها نبود):
</p>
<ul>
    <li>تغییر در کلاس <code>ReservationService</code> برای افزودن <code>case</code> جدید به دستور <code>switch</code> مربوط به پرداخت.</li>
    <li>تغییر در کلاس <code>ReservationService</code> برای افزودن <code>case</code> جدید به دستور <code>switch</code> مربوط به ارسال پیام.</li>
    <li>تغییر در کلاس <code>PaymentProcessor</code> برای افزودن متد جدید (در ساختار جدید اصلاً کلاس PaymentProcessor به آن شکل وجود ندارد).</li>
</ul>

<p>
    <strong>سوال ۲: با چند تغییر این قابلیت‌ها به پروژه اضافه می‌شدند؟</strong><br>
    در ساختار جدید (منطبق بر SOLID)، افزودن این قابلیت‌ها تنها با <strong>افزودن کلاس‌های جدید</strong> انجام می‌شود و نیازی به تغییر کدهای موجود نیست:
</p>
<ol>
    <li>ایجاد کلاس <code>SmsSender</code> (که <code>MessageSender</code> را پیاده‌سازی می‌کند).</li>
    <li>ایجاد کلاس <code>OnSitePayment</code> (که <code>PaymentStrategy</code> را پیاده‌سازی می‌کند).</li>
    <li>(تغییر جزئی در <code>Main</code>): تنها جایی که تغییر می‌کند، کلاس <code>Main</code> (یا کد کلاینت) است تا نمونه‌های جدید را به سازنده <code>ReservationService</code> تزریق کند.</li>
</ol>

<br>
<hr>
<br>

<h3>گام پنجم: نتیجه‌گیری (Step 5)</h3>
<p>
    <strong>رعایت اصول شی‌گرایی چگونه می‌تواند به بهبود قابلیت مراقبت و نگهداری (Maintainability) برنامه کمک کند؟</strong>
</p>
<p>
    بر اساس تجربه این آزمایش، رعایت اصول SOLID به دلایل زیر باعث بهبود نگهداری نرم‌افزار می‌شود:
</p>
<ul>
    <li>
        <strong>توسعه‌پذیری آسان (رعایت OCP):</strong>
        همانطور که در گام چهارم دیدیم، برای اضافه کردن ویژگی جدید (مثل روش پرداخت جدید)، نیازی نیست کد کلاس‌های اصلی و حساس (مثل <code>ReservationService</code>) را باز کرده و تغییر دهیم. این کار ریسک ایجاد باگ در کدهای قدیمی را به شدت کاهش می‌دهد.
    </li>
    <li>
        <strong>تست‌پذیری بهتر (Testability):</strong>
        به دلیل رعایت اصل <strong>DIP</strong> و <strong>SRP</strong>، کلاس‌ها کوچک‌تر شده و وابستگی‌ها از طریق اینترفیس تزریق می‌شوند. این باعث می‌شود بتوانیم برای هر کلاس به راحتی و به صورت ایزوله (با استفاده از Mock) تست واحد (Unit Test) بنویسیم.
    </li>
    <li>
        <strong>کاهش پیچیدگی و خوانایی بیشتر:</strong>
        با شکستن کلاس‌های بزرگ (God Classes) به کلاس‌های کوچکتر با مسئولیت واحد (SRP)، فهم کد برای برنامه‌نویسان جدید بسیار ساده‌تر می‌شود. هر کلاس تنها یک دلیل برای تغییر دارد و وظیفه آن مشخص است.
    </li>
    <li>
        <strong>مدیریت تغییرات (Loose Coupling):</strong>
        سیستم وابستگی کمتری دارد. تغییر در نحوه ارسال ایمیل یا تغییر در بخش پرداخت، تاثیری روی منطق رزرو هتل نمی‌گذارد، زیرا سرویس رزرو فقط با اینترفیس‌ها کار می‌کند.
    </li>
</ul>

</div>

</div>

</div>