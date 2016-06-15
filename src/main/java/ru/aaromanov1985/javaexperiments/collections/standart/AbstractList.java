package ru.aaromanov1985.javaexperiments.collections.standart;

import java.util.*;

public abstract class AbstractList<E> extends AbstractCollection<E> implements List<E> {

    private final static String name = "AbstractList:";

    protected AbstractList() {
        System.out.println(name + " конструктор AbstractList()");
    }

    public boolean add(E e) {
        System.out.println(name + " конструктор add(E e)");
        add(size(), e);
        return true;
    }

    abstract public E get(int index);

    public E set(int index, E element) {
        throw new UnsupportedOperationException();
    }

    public void add(int index, E element) {
        throw new UnsupportedOperationException();
    }

    public E remove(int index) {
        throw new UnsupportedOperationException();
    }

    public int indexOf(Object o) {
        System.out.println(name + " indexOf(Object o)");
        ListIterator<E> it = listIterator();
        if (o == null) {
            while (it.hasNext())
                if (it.next() == null)
                    return it.previousIndex();
        } else {
            while (it.hasNext())
                if (o.equals(it.next()))
                    return it.previousIndex();
        }
        return -1;
    }

    public int lastIndexOf(Object o) {
        System.out.println(name + " lastIndexOf(Object o)");
        ListIterator<E> it = listIterator(size());
        if (o == null) {
            while (it.hasPrevious())
                if (it.previous() == null)
                    return it.nextIndex();
        } else {
            while (it.hasPrevious())
                if (o.equals(it.previous()))
                    return it.nextIndex();
        }
        return -1;
    }

    public void clear() {
        System.out.println(name + " clear()");
        removeRange(0, size());
    }

    public boolean addAll(int index, Collection<? extends E> c) {
        System.out.println(name + " addAll(int index, Collection<? extends E> c)");
        rangeCheckForAdd(index);
        boolean modified = false;
        for (E e : c) {
            add(index++, e);
            modified = true;
        }
        return modified;
    }

    public Iterator<E> iterator() {
        System.out.println(name + " iterator()");
        return new Itr();
    }

    public ListIterator<E> listIterator() {
        System.out.println(name + " listIterator()");
        return listIterator(0);
    }

    public ListIterator<E> listIterator(final int index) {
        System.out.println(name + " listIterator(final int index)");
        rangeCheckForAdd(index);

        return new ListItr(index);
    }

    private class Itr implements Iterator<E> {

        int cursor = 0;

        int lastRet = -1;

        int expectedModCount = modCount;

        public boolean hasNext() {
            System.out.println(name + " hasNext()");
            return cursor != size();
        }

        public E next() {
            System.out.println(name + " next()");
            checkForComodification();
            try {
                int i = cursor;
                E next = get(i);
                lastRet = i;
                cursor = i + 1;
                return next;
            } catch (IndexOutOfBoundsException e) {
                checkForComodification();
                throw new NoSuchElementException();
            }
        }

        public void remove() {
            System.out.println(name + " remove()");
            if (lastRet < 0)
                throw new IllegalStateException();
            checkForComodification();

            try {
                AbstractList.this.remove(lastRet);
                if (lastRet < cursor)
                    cursor--;
                lastRet = -1;
                expectedModCount = modCount;
            } catch (IndexOutOfBoundsException e) {
                throw new ConcurrentModificationException();
            }
        }

        final void checkForComodification() {
            System.out.println(name + " rcheckForComodification()");
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
        }
    }

    class ListItr extends Itr implements ListIterator<E> {
        ListItr(int index) {
            System.out.println(name + " ListItr(int index)");
            cursor = index;
        }

        public boolean hasPrevious() {
            System.out.println(name + " hasPrevious()");
            return cursor != 0;
        }

        public E previous() {
            System.out.println(name + " previous()");
            checkForComodification();
            try {
                int i = cursor - 1;
                E previous = get(i);
                lastRet = cursor = i;
                return previous;
            } catch (IndexOutOfBoundsException e) {
                checkForComodification();
                throw new NoSuchElementException();
            }
        }

        public int nextIndex() {
            System.out.println(name + " nextIndex()");
            return cursor;
        }

        public int previousIndex() {
            System.out.println(name + " previousIndex()");
            return cursor - 1;
        }

        public void set(E e) {
            System.out.println(name + " set(E e)");
            if (lastRet < 0)
                throw new IllegalStateException();
            checkForComodification();

            try {
                AbstractList.this.set(lastRet, e);
                expectedModCount = modCount;
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }

        public void add(E e) {
            System.out.println(name + " add(E e)");
            checkForComodification();

            try {
                int i = cursor;
                AbstractList.this.add(i, e);
                lastRet = -1;
                cursor = i + 1;
                expectedModCount = modCount;
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }
    }

    public List<E> subList(int fromIndex, int toIndex) {
        System.out.println(name + " subList(int fromIndex, int toIndex)");
        return (this instanceof RandomAccess ?
                new RandomAccessSubList<>(this, fromIndex, toIndex) :
                new SubList<>(this, fromIndex, toIndex));
    }

    public boolean equals(Object o) {
        System.out.println(name + " equals(Object o)");
        if (o == this)
            return true;
        if (!(o instanceof List))
            return false;

        ListIterator<E> e1 = listIterator();
        ListIterator<?> e2 = ((List<?>) o).listIterator();
        while (e1.hasNext() && e2.hasNext()) {
            E o1 = e1.next();
            Object o2 = e2.next();
            if (!(o1 == null ? o2 == null : o1.equals(o2)))
                return false;
        }
        return !(e1.hasNext() || e2.hasNext());
    }

    public int hashCode() {
        System.out.println(name + " hashCode()");
        int hashCode = 1;
        for (E e : this)
            hashCode = 31 * hashCode + (e == null ? 0 : e.hashCode());
        return hashCode;
    }

    protected void removeRange(int fromIndex, int toIndex) {
        System.out.println(name + " removeRange(int fromIndex, int toIndex)");
        ListIterator<E> it = listIterator(fromIndex);
        for (int i = 0, n = toIndex - fromIndex; i < n; i++) {
            it.next();
            it.remove();
        }
    }

    protected transient int modCount = 0;

    private void rangeCheckForAdd(int index) {
        System.out.println(name + " rangeCheckForAdd(int index)");
        if (index < 0 || index > size())
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    private String outOfBoundsMsg(int index) {
        System.out.println(name + " outOfBoundsMsg(int index)");
        return "Index: " + index + ", Size: " + size();
    }
}

class SubList<E> extends AbstractList<E> {
    private final AbstractList<E> l;
    private final int offset;
    private int size;
    private final static String name = "SubList<E>:";

    SubList(AbstractList<E> list, int fromIndex, int toIndex) {
        System.out.println(name + " SubList(AbstractList<E> list, int fromIndex, int toIndex)");
        if (fromIndex < 0)
            throw new IndexOutOfBoundsException("fromIndex = " + fromIndex);
        if (toIndex > list.size())
            throw new IndexOutOfBoundsException("toIndex = " + toIndex);
        if (fromIndex > toIndex)
            throw new IllegalArgumentException("fromIndex(" + fromIndex +
                    ") > toIndex(" + toIndex + ")");
        l = list;
        offset = fromIndex;
        size = toIndex - fromIndex;
        this.modCount = l.modCount;
    }

    public E set(int index, E element) {
        System.out.println(name + " set(int index, E element)");
        rangeCheck(index);
        checkForComodification();
        return l.set(index + offset, element);
    }

    public E get(int index) {
        System.out.println(name + " get(int index)");
        rangeCheck(index);
        checkForComodification();
        return l.get(index + offset);
    }

    public int size() {
        System.out.println(name + " size()");
        checkForComodification();
        return size;
    }

    public void add(int index, E element) {
        System.out.println(name + " add(int index, E element)");
        rangeCheckForAdd(index);
        checkForComodification();
        l.add(index + offset, element);
        this.modCount = l.modCount;
        size++;
    }

    public E remove(int index) {
        System.out.println(name + " remove(int index)");
        rangeCheck(index);
        checkForComodification();
        E result = l.remove(index + offset);
        this.modCount = l.modCount;
        size--;
        return result;
    }

    protected void removeRange(int fromIndex, int toIndex) {
        System.out.println(name + " removeRange(int fromIndex, int toIndex)");
        checkForComodification();
        l.removeRange(fromIndex + offset, toIndex + offset);
        this.modCount = l.modCount;
        size -= (toIndex - fromIndex);
    }

    public boolean addAll(Collection<? extends E> c) {
        System.out.println(name + " addAll(Collection<? extends E> c)");
        return addAll(size, c);
    }

    public boolean addAll(int index, Collection<? extends E> c) {
        System.out.println(name + " addAll(int index, Collection<? extends E> c)");
        rangeCheckForAdd(index);
        int cSize = c.size();
        if (cSize == 0)
            return false;

        checkForComodification();
        l.addAll(offset + index, c);
        this.modCount = l.modCount;
        size += cSize;
        return true;
    }

    public Iterator<E> iterator() {
        System.out.println(name + " iterator()");
        return listIterator();
    }

    public ListIterator<E> listIterator(final int index) {
        System.out.println(name + " listIterator(final int index)");
        checkForComodification();
        rangeCheckForAdd(index);

        return new ListIterator<E>() {
            private final static String name = "ListIterator:";

            private final ListIterator<E> i = l.listIterator(index + offset);

            public boolean hasNext() {
                System.out.println(name + " hasNext()");
                return nextIndex() < size;
            }

            public E next() {
                System.out.println(name + " next()");
                if (hasNext())
                    return i.next();
                else
                    throw new NoSuchElementException();
            }

            public boolean hasPrevious() {
                System.out.println(name + " hasPrevious()");
                return previousIndex() >= 0;
            }

            public E previous() {
                System.out.println(name + " previous()");
                if (hasPrevious())
                    return i.previous();
                else
                    throw new NoSuchElementException();
            }

            public int nextIndex() {
                System.out.println(name + " nextIndex()");
                return i.nextIndex() - offset;
            }

            public int previousIndex() {
                System.out.println(name + " previousIndex()");
                return i.previousIndex() - offset;
            }

            public void remove() {
                System.out.println(name + " remove()");
                i.remove();
                SubList.this.modCount = l.modCount;
                size--;
            }

            public void set(E e) {
                System.out.println(name + " set(E e)");
                i.set(e);
            }

            public void add(E e) {
                System.out.println(name + " add(E e)");
                i.add(e);
                SubList.this.modCount = l.modCount;
                size++;
            }
        };
    }

    public List<E> subList(int fromIndex, int toIndex) {
        System.out.println(name + " subList(int fromIndex, int toIndex)");
        return new SubList<>(this, fromIndex, toIndex);
    }

    private void rangeCheck(int index) {
        System.out.println(name + " rangeCheck(int index)");
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    private void rangeCheckForAdd(int index) {
        System.out.println(name + " rangeCheckForAdd(int index)");
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    private String outOfBoundsMsg(int index) {
        System.out.println(name + " outOfBoundsMsg(int index)");
        return "Index: " + index + ", Size: " + size;
    }

    private void checkForComodification() {
        System.out.println(name + " checkForComodification()");
        if (this.modCount != l.modCount)
            throw new ConcurrentModificationException();
    }
}

class RandomAccessSubList<E> extends SubList<E> implements RandomAccess {
    final static String name = "RandomAccessSubList<E>";

    RandomAccessSubList(AbstractList<E> list, int fromIndex, int toIndex) {
        super(list, fromIndex, toIndex);
        System.out.println(name + " checkForComodification()");
    }

    public List<E> subList(int fromIndex, int toIndex) {
        System.out.println(name + " subList(int fromIndex, int toIndex)");
        return new RandomAccessSubList<>(this, fromIndex, toIndex);
    }
}