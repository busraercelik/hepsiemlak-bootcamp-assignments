# Spring Framework'te Kullanılan Tasarım Kalıpları

Tasarım kalıpları, belirli bir bağlamda yaygın olarak ortaya çıkan soruna genel ve yeniden kullanılabilir çözümdür. Burada çözüm olarak kodumuza direk dahil edeceğimiz bir kalıptan bahsetmiyoruz. Farklı durumlarda oluşabilecek problemin nasıl çözüleceğine ilişkin bir tanım ya da kalıptır. Bir uygulama ya da sistem tasarlarken yaygın olarak görülen problemlere bulunan en uygun çözümlerdir.


1- Singleton Pattern

Singleton deseni, uygulamada sadece tek bir nesnenin oluşturulmasını sağlayan bir çözümdür.

Genelde singleton bean'i tüm uygulama için benzersizdir. Ancak Spring, her bir Spring IoC container başına tek bir obje üretir. Bu kapsamda Spring'in yaklaşımı singleton'ın katı tanımından farklılık gösterir. Bu sebeple eğer bir uygulamada birden fazla container varsa  aynı sınıfa ait birden çok nesne oluşabilir.

Varsayılan olarak Spring bütün bean'leri singleton olarak oluşturur. Bir örnekle kafamızda canlandıralım.


Configuration sınıfı
```
@Configuration
public class DefaultConfig {

    @Bean
    public UserService userService() {
        return new UserService();
    }
}
```

Servis sınıfı
```
public class UserService {
}
```

```
ApplicationContext applicationContext = new AnnotationConfigApplicationContext(DefaultConfig.class);
        UserService userService1 = applicationContext.getBean(UserService.class);
        UserService userService2 = applicationContext.getBean(UserService.class);

        System.out.println(userService1.hashCode());
        System.out.println(userService2.hashCode());
        assertEquals(userService1, userService2);
```
Çıktısı:

```
1168420930
1168420930
```
UserService sınıfından oluşturulan bean'lerin aynı olduğu yukarıdaki sonuçta görülnektedir.

Eğer bu scope'un değiştirilmesini istiyorsak
```
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
```
anotasyonunu kullanabiliriz. Yukarıdaki koda sadece bu Scope'u ekleyelim ve her seferinde yeni bir nesnenin oluştuğunu görelim.

```
@Configuration
public class DefaultConfig {

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public UserService userService() {
        return new UserService();
    }
}
``` 

Çıktısı:
```
1943634922
916835004
```

2- Factory Method Pattern

Spring bu model çerçevesinde, BeanFactory ve ApplicationContext kullanarak bean'leri oluşturur.

Temelde Spring bean container'ı bean üreten factory gibi davranır. Bu yüzden BeanFactory interface'i bean container'ın soyutlaması olarak tanımlanır.

```
public interface BeanFactory {

    getBean(Class<T> requiredType);
    getBean(Class<T> requiredType, Object... args);
    getBean(String name);

    // ...
]
```

Her bir getBean metodu bir factory metodu olarak alınır. Metoda verilen parametreyle eşleşen bir bean döndürür geriye. Spring bean container'ı başlatmak için XML ya da Java anotasyonları gibi bir konfigürasyona ihtiyaç duyar.

Yukarda kullandığımız AnnotationConfigApplicationContext, ApplicationContext'in bir implementasyonudur. BeanFactory interface'inden miras alınan çaşitli factory metodlarıyla bean oluşturabiliriz.

```
@Configuration
@ComponentScan(basePackageClasses = DefaultConfig.class)
public class DefaultConfig {
}
```

```
@AllArgsConstructor
@Data
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class Account {
   private String name;
}
```

```
 String expectedName = "salary";
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(DefaultConfig.class);
        Account contextBean = applicationContext.getBean(Account.class, expectedName);
        assertNotNull(contextBean);
        assertEquals(contextBean.getName(), expectedName);
```

3- Template Pattern

Birçok frameworkte kodun büyük bir kısmı boilerplate code'dur. Örneğin, bir veritabanında bir sorgu yürütülürken, aynı adımlar dizisi tamamlanmalıdır:

1- Bağlantı kurun
2- Sorguyu çalıştır
3- Temizleme gerçekleştirin
4- Bağlantıyı kapat

Bu model, yaygın olarak tekrarlanan kodlarla başa çıkmak için kullanılır (bağlantıları temiz bir şekilde kapatmak, vb.). Örneğin JdbcTemplate, JmsTemplate, JpaTemplate. Bu pattern behavior pattern kategorisine girer.

Game.java
```
public abstract class Game {
   abstract void initialize();
   abstract void startPlay();
   abstract void endPlay();

   //template method
   public final void play(){

      //initialize the game
      initialize();

      //start game
      startPlay();

      //end game
      endPlay();
   }
}
```

Cricket.java
```
public class Cricket extends Game {

   @Override
   void endPlay() {
      System.out.println("Cricket Game Finished!");
   }

   @Override
   void initialize() {
      System.out.println("Cricket Game Initialized! Start playing.");
   }

   @Override
   void startPlay() {
      System.out.println("Cricket Game Started. Enjoy the game!");
   }
}
```

Football.java
```
public class Football extends Game {

   @Override
   void endPlay() {
      System.out.println("Football Game Finished!");
   }

   @Override
   void initialize() {
      System.out.println("Football Game Initialized! Start playing.");
   }

   @Override
   void startPlay() {
      System.out.println("Football Game Started. Enjoy the game!");
   }
}
```

TemplatePatternDemo.java
```
public class TemplatePatternDemo {
   public static void main(String[] args) {

      Game game = new Cricket();
      game.play();
      System.out.println();
      game = new Football();
      game.play();		
   }
}
```

4- Model View Controller Pattern

Model-View-Controller (MVC) yazılım tasarım modeli, bir yazılım uygulamasındaki yapıyı ayırmak için (seperation of concerns) bir yöntemdir. Prensipte, uygulama mantığı ,  görünüm katmanından ayrılır. Model, controller ve görünüm katmanları arasında bir iletişim aracıdır.

5 - Inversion Of Control (IoC)

Spring framework'ü, nesnenin oluşturulmasından, nesnelerin birbirine bağlanmasından, bu nesneleri konfigüre etmekten ve nesne yok olana kadar nesnenin tüm hayat döngüsünden sorumlu olan bir IOC konteynerine sahiptir. Spring Beanleri kontrol etmek için kullanılır. Sınıflar arasında tightly coupling sorununa çözümdür. Bean oluşturma sorumluluğu Spring'e verilir.