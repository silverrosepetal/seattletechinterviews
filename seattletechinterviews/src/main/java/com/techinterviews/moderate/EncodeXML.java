package com.techinterviews.moderate;

import org.w3c.dom.Attr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Created by xeniah on 3/13/15.
 */
/*
Since ZML is very verbose, you are given a way of encoding it where each tag gets mapped to a pre-defined integer value.
The language/grammar is as follows:
    Element --> Tag Attributes END Children END
    Attribute --> Tag Value
    END --> 0
    Tag --> some predefined mapping to int
    Value --> string value
    END

For example, the following XML might be converted into the compressed string below(assuming a mapping of
    family -> l
    person ->2,
    firstName -> 3,
    lastName -> 4
    state -> 5).
<family lastName="McDowell" state="CA">
  <person firstName="Gayle">Some Message</person>
</family>
Becomes:
1 4 McDowell 5 CA 0 2 3 Gayle 0 Some Message 0 0
Write code to print the encoded version of an XML element (passed in Element and Attribute objects).

 */
public class EncodeXML {


    public static String encodeXML(Element element, HashMap<String, Integer> map)
    {
        StringBuilder builder = new StringBuilder();
        builder.append(map.get(element.getTag())+ " ");

        if(element.getAttributes() != null)
        {
            for(Attribute a :  element.getAttributes())
            {
                builder.append(map.get(a.getTag()) + " " + a.getValue() + " ");
            }
        }

        builder.append(" 0 ");

        if(element.getChildren() != null)
        {
            for(Element e : element.getChildren())
            {
                builder.append(encodeXML(e, map));
            }
        }

        if(element.getValue() != null) {
            builder.append(element.getValue());
        }
        builder.append(" 0 ");

        return builder.toString();
    }

    public static List<String> getWords(List<String> dictionary, String matchWord)
    {
        List<String> result = new ArrayList<String>();
        HashMap<String, Boolean> wordMap = new HashMap<String, Boolean>();

        for(String s : dictionary)
        {
            wordMap.put(s, true);

            if(s.contains(matchWord))
            {
                String temp = s.replace(matchWord, "");
                result.add(temp);
            }
        }

        Iterator<String> iter = result.iterator();
        
        while(iter.hasNext())
        {
            if(wordMap.get(iter.next()) == null)
            {
                iter.remove();
            }
        }
        return result;
    }

    public static void main(String[] args) {
        List<Attribute> attributesFamily = new ArrayList<Attribute>();
        List<Attribute> attributesPerson = new ArrayList<Attribute>();

        attributesFamily.add(new Attribute("lastName", "Newton"));
        attributesFamily.add(new Attribute("state", "UK"));

        attributesPerson.add(new Attribute("firstName", "Isaac"));

        Element person = new Element(attributesPerson, null);
        person.setValue("I think I see an Apple up there");
        person.setTag("person");

        List<Element> children = new ArrayList<Element>();
        children.add(person);
        Element family = new Element(attributesFamily, children);
        family.setTag("family");


        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put("family", 1);
        map.put("person", 2);
        map.put("firstName", 3);
        map.put("lastName", 4);
        map.put("state", 5);
        map.put("end", 0);


        System.out.print(encodeXML(family, map));
    }


    public static class Element
    {
        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public List<Attribute> getAttributes() {
            return attributes;
        }

        public void setAttributes(List<Attribute> attributes) {
            this.attributes = attributes;
        }

        public List<Element> getChildren() {
            return children;
        }

        public void setChildren(List<Element> children) {
            this.children = children;
        }

        String tag;
        String value;
        List<Attribute> attributes;
        List<Element> children;

        public Element(List<Attribute> attributes, List<Element> children)
        {
            this.attributes = attributes;
            this.children = children;
        }


    }

    public static class Attribute{
        String value;
        String tag;

        public Attribute(String tag, String value)
        {
            this.value = value;
            this.tag = tag;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }
    }
}
