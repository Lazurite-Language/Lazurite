# Библиотеки
* [graph - графическая библиотека](#graph)
* [http - работа с сетью](#http)
* [lsoup - работа с HTML ](#lsoup)
* [reflection - внедрение java пакетов](#reflection)
* [gforms - графическая библиотека (виджеты)](#gforms)
* [jloader - загрузка jar файлов в lazurite](#jloader)
* [arrays - работа с массивами](#arrays)
* [async](#async)
* [std](#std)
* [artify](#artify)
* [base64](#base64)
* [clipboard](#clipboard)
* [colors](#colors)
* [iceCream](#iceCream)
* [json](#json)
* [LFS - работа с файлами](#LFS)
* [ML](#ML)
* [random](#random)
* [system](#system)
* [time](#time)

# graph
## Библиотека для работы с графикой.

### Окно
Frame(arg[0], arg[1], arg[2]) - создает окно с заголовком arg[0] и размерами arg[1], arg[2].
Все аргументы опциональны и указывать их необязательно, ведь в таком случае будут выбраны заранее заготовленные аргументы. 

````java
using "graph"
width = 640
height = 400
Frame("test",width,height)
````
Функции окна:
````
background(r,g,b) - установка цвета фона
Redraw() - обновление экрана
````
### Примитивы
ellipse(x,y,w,h) - создает закрашенный эллипс в координатах x, y с размерами w, h

lellipse(x,y,w,h) - создает эллипс в координатах x, y с размерами w, h

rect(x,y,w,h) - создает закрашенный прямоугольник в координатах x, y с размерами w, h

lrect(x,y,w,h) - создает прямоугольник в координатах x, y с размерами w, h

fill(rgb) - закрашивает все фигуры, идущие после него 

````java
using "graph"
Frame()

fill(100,100,200)
rect(10,10,200,100)

fill(100,200,100)
lrect(100,100,100,100)
````


### Текст
text("text", x, y) - создает текст в координатах x, y
````java
using "graph"
Frame(500,500)
fill(0)
text("Hello",100,100)
````
font("Arial", size) - устанавливает шрифт с определенным размером
````java
using "graph"
Frame(500,500)

font("Arial",30)

fill(0)
text("Hello",100,100)
````
### Обработка нажатий
keyPressed()
````java
using "graph"
Frame(500,500)

while(1){
    key = keyPressed()
    if(key == KEY.LEFT){
        println("left")
    }else if(key == KEY.A){
        println("a")
    }
}
````
mouseHover()
````java
using "graph"

Frame()
mouse = mouseHover()

while(1){
    background(100,100,200)
    fill(255,255,255)
    ellipse(mouse[0], mouse[1], 50,50)
    Redraw()
}
````
        
        

### Другие функции
````
translate(arg[0], arg[1])
dispose()
rotate(arg[0]) || rotate(arg[0], arg[1], arg[2])
scale(arg[0], arg[1])
````
# http
http.download() - загружает файл из downloadUrl в filePath
````java
using "http"
http.download("https://test/tester.html", "test.txt")
````


http.getContentLength(url) - получает длину содержимого, отправляя запрос HEAD на указанный URL-адрес

http.request(url) - выполняет GET-запрос на указанный адрес url.

http.request(url, method) - выполняет запрос на указанный адрес url методом method (GET, POST, PUT, DELETE, PATCH, OPTIONS).

http.request(url, callback) - выполняет GET-запрос на указанный адрес url, ответ сервера передаёт в функцию callback.
````java
http.request("https://github.com/ArtyomKingmang/Lazurite/blob/main/docs/RU.md", ::echo)
````
http.request(url, method, params) - выполняет запрос на указанный адрес url, методом method c данными params (объект).

http.request(url, method, callback) - выполняет запрос на указанный адрес url, методом method, ответ сервера передаёт в функцию callback.

http.request(url, method, params, callback) - выполняет запрос на указанный адрес url, методом method, с данными params, ответ сервера передаёт в функцию callback.

http.request(url, method, params, options, callback) - выполняет запрос на указанный адрес url, методом method, с данными params, параметрами подключения options, ответ сервера передаёт в функцию callback.

Параметрами подключения выступает объект. Допустимы следующие параметры

    header - задаёт http-заголовок, если передана строка или несколько заголовков, если массив.

    encoded - указывает, что данные params уже закодированы в URL-формате.

    content_type - указывает Content-Type.

    extended_result - указывает, что ответ сервера нужно вернуть в расширенном виде, а именно объектом с данными:

        text - текст ответа сервера

        message - сообщение сервера

        code - код ответа сервера

        headers - http-заголовки ответа

        content_length - Content-Length

        content_type - Content-Type

url.encode(str) - преобразует строку в URL-формат

# lsoup
lsoup.parse(url) - загружает HTML-код веб-страницы по указанному URL.

lsoup.select("tag") выполняет поиск элементов с тегом "tag" в загруженном HTML-коде.

````java
using "lsoup"

lsoup.parse("https://artyomkingmang.github.io/lazurite-pages/")
result = lsoup.select("title")

print(result)
````

# reflection
Работа с пакетами java и конвертацией java в lazurite (и наоборот) 

JClass(arg) - создает новый JavaClassValue

````java
using "reflection"
Locale = JClass("java.util.Locale")

locale1 = new Locale("EN", "INDIA");
println("Locale: " + locale1)

println("Country Name: " + locale1.getDisplayCountry());
````

JObject(arg) - конвертирует LZRValue в java объект

LZRValue(arg) - конвертирует java объект в LZRValue

# gforms
* [Виджеты](#Виджеты)
* [Функции всех виджетов](#Функции-всех-виджетов)
* [Функции Frame](#Функции-Frame)
* [Функции Label](#Функции-Label)
* [Layouts](#Layouts)
  * [Методы layout](#Методы-layout)
  * [BorderLayout](#BorderLayout)
  * [BoxLayout](#BoxLayout)
## Виджеты
Frame(title = "") - создает новое окно

Button(text = "") - создает новую кнопку

ProgressBar(isVertical = true, min, max) - создает новый индикатор

ScrollPane(view, vsbPolicy, hsbPolicy) - создает новую панель прокрутки

Label(text = "", SwingConstants.LEADING) - создает новый текст

Panel(layoutManager) -  создает новую панель с дополнительным менеджером макетов

TextField(text = "") - создает новое текстовое поле

TextArea(text = "") - создает новую текстовую область
````java
using "gforms"

button = Button("Button")

window = Frame("widgets")
window.setMinimumSize(600,300)
window.add(button)
window.pack()
window.setVisible()
````
## Функции всех виджетов
````
setFont(Font)
getFont()
onKeyAction(Function)
addKeyListener(Function)
getFocusTraversalKeysEnabled()
getHeight()
getIgnoreRepaint()
getLocation()
getLocationOnScreen()
getMinimumSize()
getMaximumSize()
getName()
getPreferredSize()
getSize()
getWidth()
getX()
getY()
hasFocus()
invalidate()
isDisplayable()
isDoubleBuffered()
isEnabled()
isFocusOwner()
isFocusable()
isLightweight()
isOpaque()
isShowing()
isValid()
isVisible()
requestFocus()
requestFocusInWindow()
repaint()
revalidate()
setMaximumSize(w, h)
setMinimumSize(w, h)
setName(name = "")
setPreferredSize(w, h)
setSize(w, h)
setVisible(bool = true)
setLocation(x, y)
validate()
````

## Функции Frame
````
getTitle()
getResizable()
getDefaultCloseOperation()
setDefaultCloseOperation()
setResizable(boolean = true)
setTitle(title = "")
````

## Функции Label
````
getDisplayedMnemonic()
getDisplayedMnemonicIndex()
getHorizontalAlignment()
getHorizontalTextPosition()
getIconTextGap()
getVerticalAlignment()
getVerticalTextPosition()
getText()
setDisplayedMnemonic(mnemonic = '')
setDisplayedMnemonicIndex(index = 0)
setHorizontalAlignment(SwingConstants.LEFT)
setHorizontalTextPosition(SwingConstants.CENTER)
setVerticalAlignment(SwingConstants.NORTH)
setVerticalTextPosition(SwingConstants.BOTTOM)
setText(text = "")
````

## Layouts

### Методы layout
````
borderLayout(hgap = 0, vgap = 0) - создаёт BorderLayout
boxLayout(panel, axis = BoxLayout.PAGE_AXIS) - создаёт BoxLayout
cardLayout(hgap = 0, vgap = 0) - создаёт CardLayout
flowLayout(align = FlowLayout.CENTER, hgap = 5, vgap = 5) - создаёт FlowLayout
gridLayout(rows = 1, cols = 0, hgap = 0, vgap = 0) - создаёт GridLayout
````

Пример
````java
using "gforms"

window = Frame("test")
window.setMinimumSize(600,300)

panel = Panel(layoutManager = borderLayout())

field = TextField("")
field.setFont("Tahoma",0,28)

panel.add(field,BorderLayout.NORTH)
second_panel = Panel(layoutManager = gridLayout(rows=4, cols=4))
for(i = 0, i < 4, i++){
second_panel.add(Button(i))
}

window.add(panel,BorderLayout.NORTH)
window.add(second_panel,BorderLayout.CENTER)
window.pack()
window.setVisible()
````
### BorderLayout:
````

AFTER_LINE_ENDS=After;

LINE_END=After;

LINE_START=Before;

BEFORE_LINE_BEGINS=Before;

CENTER=Center;

EAST=East;

BEFORE_FIRST_LINE=First;

PAGE_START=First;

AFTER_LAST_LINE;

PAGE_END;

NORTH;

SOUTH;

WEST
````
### BoxLayout:
````
X_AXIS;

Y_AXIS;

LINE_AXIS;

PAGE_AXIS
````
