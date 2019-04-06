

## 自定义两个bean的特殊转换

```java
@Component
public class PersonDtoToPersonMapper extends CustomMapper<PersonDTO, Person> {
 
    @Override
    public void mapAtoB(PersonDTO a, Person b, MappingContext context) {
        b.setId(a.getId());
        b.setName(a.getName());
        b.setSurnames(a.getLastNames());
        //如果需要映射某个特定字段，则可以使用受保护的mapperFacade
        //this.mapperFacade.map(sourceObject, destinationClass);
        Address address = new Address();
        address.setStreet(a.getStreetAddress());
        address.setCity(a.getCity());
        address.setZipCode(a.getPostalCode());
        b.setAddress(address);
    }
    @Override
    public void mapBtoA(Person b, PersonDTO a, MappingContext context) {
        a.setId(b.getId());
        a.setName(b.getName());
        a.setLastNames(b.getSurnames());
        Address address = b.getAddress();
        if (address != null) {
            a.setStreetAddress(address.getStreet());
            a.setCity(address.getCity());
            a.setPostalCode(address.getZipCode());
        }
    }
 
}

```

## 两个属性之间的转换 
####（基础类型的转换不建议使用 可能会影响到其他类的bean copy操作（这部分操作往往是不希望遇到的））
#### 自定义类型到基础类型一般都是自己希望的转换 无所谓
## 完善使用 不推荐非spring模式运行


## modelMapper

>> extends Convert即可

>> 调用 a.convert(B.class);

>> 特殊转换自己进行 或者自己实现注册

