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
      <td style="border: 1px solid #dddddd; padding: 8px;">MessageSender</td>
      <td style="border: 1px solid #dddddd; padding: 8px; color: red;"><strong>مورد نقض</strong></td>
      <td style="border: 1px solid #dddddd; padding: 8px;">اینترفیس نام متد <code>sendEmail</code> را اجبار می‌کند. اگر بخواهیم <code>SmsSender</code> داشته باشیم، پیاده‌سازی متدی با نام "sendEmail" برای ارسال پیامک بی‌معنی و گیج‌کننده است.</td>
    </tr>
    <tr>
      <td style="border: 1px solid #dddddd; padding: 8px;"><strong>DIP</strong><br><span style="font-size: 0.8em; color: #555;">(Dependency Inversion)</span></td>
      <td style="border: 1px solid #dddddd; padding: 8px;">ReservationService</td>
      <td style="border: 1px solid #dddddd; padding: 8px; color: red;"><strong>مورد نقض</strong></td>
      <td style="border: 1px solid #dddddd; padding: 8px;">ماژول سطح بالا (سرویس رزرو) مستقیماً به ماژول‌های سطح پایین (مثل کلاس‌های واقعی <code>EmailSender</code> و <code>PaymentProcessor</code>) وابسته است و از <code>new</code> استفاده می‌کند، به جای اینکه به Abstraction وابسته باشد.</td>
    </tr>
     <tr>
      <td style="border: 1px solid #dddddd; padding: 8px;"><strong>PLK</strong><br><span style="font-size: 0.8em; color: #555;">(Law of Demeter)</span></td>
      <td style="border: 1px solid #dddddd; padding: 8px;">ReservationService</td>
      <td style="border: 1px solid #dddddd; padding: 8px; color: red;"><strong>مورد نقض</strong></td>
      <td style="border: 1px solid #dddddd; padding: 8px;">دسترسی زنجیره‌ای به ویژگی‌های اشیاء دیگر. مثال: <code>res.customer.name</code> یا <code>res.room.price</code>. کلاس سرویس نباید از ساختار داخلی <code>Reservation</code> خبر داشته باشد.</td>
    </tr>

  </tbody>
</table>

</div>