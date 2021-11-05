package GUI;

public interface Node<E> {

    E getElement();
    Node<E> getNext();
    void setElement(E elem);
    void setNext(Node<E> next);

}
