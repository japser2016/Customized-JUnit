import java.util.*;
import java.lang.annotation.*;
import java.lang.reflect.*;
///
//import java.lang.*;
//import java.lang.Class.*;

public class Unit {
	public static boolean duplicate_Annotation(ArrayList<Method> tests, ArrayList<Method> before_class, ArrayList<Method> befores, ArrayList<Method> after_class, ArrayList<Method> afters){
		Set<Method> newSet = new HashSet<Method>();
		
		Set<Method> tSet = new HashSet<Method>(tests);		
		Set<Method> bcSet = new HashSet<Method>(before_class);
		Set<Method> bSet = new HashSet<Method>(befores);
		Set<Method> acSet = new HashSet<Method>(after_class);
		Set<Method> aSet = new HashSet<Method>(afters);
		
		newSet.addAll(tSet);
		newSet.addAll(bcSet);
		newSet.addAll(bSet);
		newSet.addAll(acSet);
		newSet.addAll(aSet);
		
		int original = tests.size() + before_class.size() + befores.size() + after_class.size() + afters.size();
		//int original = tSet.size() + bcSet.size() + bSet.size() + acSet.size() + aSet.size();
		int now = newSet.size();
		
		return (now < original);
	}
	
    public static Map<String, Throwable> testClass(String name) {
	//throw new UnsupportedOperationException();
		/*
		try{
			Map<String, Throwable> result = new HashMap<>();
			Class<?> tarClass = Class.forName(name);
			Method[] methods = tarClass.getMethods();
			for (Method m : methods){
				Annotation anno = m.getAnnotation(Test.class);
				if (anno != null){
					result.put(m.getName(), null);
				}
			}
			return result;
		} catch (ClassNotFoundException e){
			//throw new RuntimeException("ClassNotFoundException -> " + e.getMessage());
			throw new RuntimeException(e);
		}
		*/
		////////////
		HashMap<String, Throwable> result = new HashMap<>();
		ArrayList<Method> tests = new ArrayList<Method>();
		ArrayList<Method> before_class = new ArrayList<Method>();
		ArrayList<Method> befores = new ArrayList<Method>();
		ArrayList<Method> after_class = new ArrayList<Method>();
		ArrayList<Method> afters = new ArrayList<Method>();
		
		
		try{
			Class<?> c = Class.forName(name);
			
			Constructor<?> cons = c.getConstructor();
			Object o = cons.newInstance();

			Method[] methods = c.getMethods();
			Arrays.sort(methods, new Comparator<Method>(){
				public int compare(Method m1, Method m2)
				{
					return m1.getName().compareTo(m2.getName());
				}
			});
			for (int i = 0; i < methods.length; i++)
			{
				if (methods[i].getAnnotation(Test.class) != null)
				{
					tests.add(methods[i]);
				} 
				if (methods[i].getAnnotation(BeforeClass.class) != null)
				{
					if (!Modifier.isStatic(methods[i].getModifiers()))
					{
						throw new RuntimeException("BeforeClass Method should be static!");
					}
					before_class.add(methods[i]);
				}
				if (methods[i].getAnnotation(Before.class) != null)
				{
					befores.add(methods[i]);
				}
				if (methods[i].getAnnotation(AfterClass.class) != null)
				{
					if (!Modifier.isStatic(methods[i].getModifiers()))
					{
						throw new RuntimeException("AfterClass Method should be static!");
					}
					after_class.add(methods[i]);
				}
				if (methods[i].getAnnotation(After.class) != null)
				{
					afters.add(methods[i]);
				}
				
				if (duplicate_Annotation(tests, before_class, befores, after_class, afters))
				{
					throw new RuntimeException("duplicate Annotations among the five annotations!");
				}
				
			}
			for (int i = 0; i < before_class.size(); i++)
			{
				System.out.println(before_class.get(i));
				Object t = before_class.get(i).invoke(o);
			}
			
			for (int i = 0; i < tests.size(); i++)
			{
				for (int j = 0; j < befores.size(); j++)
				{
					System.out.println(befores.get(j));
					Object t = befores.get(j).invoke(o);
				}
				
				try{
					System.out.println(tests.get(i));					
					Object t = tests.get(i).invoke(o);
					result.put(tests.get(i).getName(),null);
					
				} catch (Exception e){
					result.put(tests.get(i).getName(), e.getCause());
					System.out.println(e.getCause());
				}
				System.out.println(result);
				
				for (int k = 0; k < afters.size(); k++)
				{
					System.out.println(afters.get(k));
					Object t = afters.get(k).invoke(o);
				}
			}
			for (int i = 0; i < after_class.size(); i++)
			{
				System.out.println(after_class.get(i));
				Object t = after_class.get(i).invoke(o);
			}
			
		} catch (Exception e){
			throw new RuntimeException(e); 
		}
		
		return result;
    }
	
	static ArrayList<Annotation> generate_nested_annotation_list(AnnotatedType tp, ArrayList<Annotation> anno_list){
		for (Annotation anno : tp.getDeclaredAnnotations()){
			anno_list.add(anno);
		}
		if (tp instanceof AnnotatedParameterizedType){
			for (AnnotatedType anno_tp : ((AnnotatedParameterizedType)tp).getAnnotatedActualTypeArguments())
			generate_nested_annotation_list(anno_tp, anno_list);
		}
		return anno_list;
	}
	
	static ArrayList<Object> generate_permutations_length(ArrayList<Object> ll, int len, ArrayList<Object> pers){
		if (len == 0){
			ArrayList<Object> temp = new ArrayList<Object>();
			pers.add(temp);
			return pers;
		} else {
			ArrayList<Object> temp = generate_permutations_length(ll, len-1, pers);
			pers = new ArrayList<Object>();
			for (int i = 0; i < temp.size(); i++){
				for (int j = 0; j < ll.size(); j++){
					ArrayList<Object> new_temp = new ArrayList<Object>();					
					new_temp.add(ll.get(j));
					/*
					for (int k = 0; k < temp.get(i).size(); k++){
						new_temp.add(temp.get(i).get(k));
					}
					*/
					new_temp.addAll((List)(temp.get(i)));					
					pers.add(new_temp);
				}
			}
			return pers;
		}
	}
	
	static ArrayList<Object> generate_one_parameter_list(ArrayList<Annotation> nested_annotations, ArrayList<Object> para_choices, Class<?> c, Object o){	
		if (!nested_annotations.isEmpty()){
			Annotation ann = nested_annotations.get(0);
			if (IntRange.class.isInstance(ann)){
				IntRange argumentAnnotation = (IntRange) ann;
				int min = argumentAnnotation.min();
				int max = argumentAnnotation.max();				
				for (int ii = min; ii <= max; ii++)
				{
					para_choices.add(ii);
				}
			} else if (StringSet.class.isInstance(ann)){
				StringSet argumentAnnotation = (StringSet) ann;
				String[] strings = argumentAnnotation.strings();
				int length = strings.length;				
				for (int ii = 0; ii < length; ii++)
				{
					para_choices.add(strings[ii]);
				}				
			} else if (ForAll.class.isInstance(ann)){
				ForAll argumentAnnotation = (ForAll) ann;
				String meth_name = argumentAnnotation.name();
				int times = argumentAnnotation.times();				
				for (int ii = 0; ii < times; ii++){
					Object arg = null;
					try{
						Method meth = c.getMethod(meth_name);
						arg = meth.invoke(o);
						para_choices.add(arg);													
					} catch (Exception e){
						throw new RuntimeException(e);
					}
				}			
			} else if (ListLength.class.isInstance(ann)){
				ListLength argumentAnnotation = (ListLength) ann;
				int min = argumentAnnotation.min();
				int max = argumentAnnotation.max();	
				if (nested_annotations.size() <= 1){
					ArrayList<Object> temp = new ArrayList<>();
					para_choices.add(temp);
					return para_choices;
				}
				nested_annotations.remove(0);
				ArrayList<Object> temp = generate_one_parameter_list(nested_annotations, para_choices, c, o);
				para_choices = new ArrayList<Object>();
				for (int len = min; len <= max; len++){
					ArrayList<Object> pers = new ArrayList<Object>();
					pers = generate_permutations_length(temp, len, pers);
					para_choices.addAll(pers);					
				}
			}
			
		}
		return para_choices;
	}

    public static Map<String, Object[]> quickCheckClass(String name) {
	//throw new UnsupportedOperationException();
		HashMap<String, Object[]> result = new HashMap<>();
		ArrayList<Method> properties = new ArrayList<Method>();
		
		try {
			Class<?> c = Class.forName(name);			
			Constructor<?> cons = c.getConstructor();
			Object o = cons.newInstance();
			
			Method[] methods = c.getMethods();
			Arrays.sort(methods, new Comparator<Method>(){
				public int compare(Method m1, Method m2)
				{
					return m1.getName().compareTo(m2.getName());
				}
			});
			
			for (int i = 0; i < methods.length; i++)
			{
				if (methods[i].getAnnotation(Property.class) != null)
				{					
					properties.add(methods[i]);
				} 
			}
			
			for (int i = 0; i < properties.size(); i++){
				
				////////////////////////
				System.out.println("\n" + properties.get(i));
				//result.put(properties.get(i).getName(), null);
				ArrayList<Object> l = new ArrayList<Object>();
				Annotation[][] annotations = properties.get(i).getParameterAnnotations();
				Parameter[] parameters = properties.get(i).getParameters();
				
				ArrayList<Object> all_parameters = new ArrayList<Object>();
				for (int j = 0; j < annotations.length; j++)
				{
					ArrayList<Object> para_choices = new ArrayList<Object>();
					for (int p = 0; p < annotations[j].length; p++)
					{
						Annotation ann = annotations[j][p];
						//System.out.println(ann);
						AnnotatedType anno_type = parameters[j].getAnnotatedType();
						ArrayList<Annotation> annotations_for_para = new ArrayList<>();
						annotations_for_para = generate_nested_annotation_list(anno_type, annotations_for_para);
						System.out.println(annotations_for_para);
						//ArrayList<Object> para_choices = new ArrayList<Object>();
						para_choices = new ArrayList<Object>();
						para_choices = generate_one_parameter_list(annotations_for_para, para_choices, c, o);
						System.out.println(para_choices);
						
						if (parameters.length == 1){
							for (int g = 0; g < Math.min(100, para_choices.size()); g++){
								try{
									if ((boolean)(properties.get(i).invoke(o, para_choices.get(g))) == false)
									{
										l.add(para_choices.get(g));
									}
								} catch(Exception e){
									l.add(para_choices.get(g));
								}
							}	
						}
					}
					all_parameters.add(para_choices);
				}
				
				if (parameters.length == 2){
					//for (int g = 0; g < Math.min(100, para_choices.size()); g++){
					int ct = 0;
					for (int j = 0; (j < ((List)(all_parameters.get(0))).size()) && (ct<100); j++){
						for (int k = 0; (k < ((List)(all_parameters.get(1))).size()) && (ct<100); k++){
							ArrayList<Object> temp = new ArrayList<Object>();
							temp.add(((List)(all_parameters.get(0))).get(j));
							temp.add(((List)(all_parameters.get(1))).get(k));
							ct++;
							try{
								if ((boolean)(properties.get(i).invoke(o, ((List)(all_parameters.get(0))).get(j), ((List)(all_parameters.get(1))).get(k))) == false)
								{									
									//l.add(temp);
									l.addAll(temp);
									
								}
							} catch(Exception e){
								//l.add(temp);
								l.addAll(temp);
							}
						}
					}
				}
				
				if (parameters.length == 3){
					//for (int g = 0; g < Math.min(100, para_choices.size()); g++){
					int ct = 0;
					for (int j = 0; (j < ((List)(all_parameters.get(0))).size()) && (ct<100); j++){
						for (int k = 0; (k < ((List)(all_parameters.get(1))).size()) && (ct<100); k++){
							for (int m = 0; (m < ((List)(all_parameters.get(2))).size()) && (ct<100); m++){
								ArrayList<Object> temp = new ArrayList<Object>();
								temp.add(((List)(all_parameters.get(0))).get(j));
								temp.add(((List)(all_parameters.get(1))).get(k));
								temp.add(((List)(all_parameters.get(2))).get(m));
								ct++;
								try{
									if ((boolean)(properties.get(i).invoke(o, ((List)(all_parameters.get(0))).get(j), ((List)(all_parameters.get(1))).get(k), ((List)(all_parameters.get(2))).get(m))) == false)
									{									
										//l.add(temp);
										l.addAll(temp);
										
									}
								} catch(Exception e){
									//l.add(temp);
									l.addAll(temp);
								}
							}
						}
					}
				}
				
				Object[ ] array = l.toArray( );
				if (array.length == 0){
					result.put(properties.get(i).getName(), null);
				} else{
					result.put(properties.get(i).getName(), array);
				}				
			}
		} catch (Exception e){
			throw new RuntimeException(e); 
		}
		return result;
    }
}