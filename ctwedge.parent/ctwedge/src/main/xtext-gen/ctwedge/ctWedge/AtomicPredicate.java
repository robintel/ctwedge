/**
 * generated by Xtext 2.12.0
 */
package ctwedge.ctWedge;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Atomic Predicate</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link ctwedge.ctWedge.AtomicPredicate#getBoolConst <em>Bool Const</em>}</li>
 *   <li>{@link ctwedge.ctWedge.AtomicPredicate#getName <em>Name</em>}</li>
 * </ul>
 *
 * @see ctwedge.ctWedge.CtWedgePackage#getAtomicPredicate()
 * @model
 * @generated
 */
public interface AtomicPredicate extends Expression
{
  /**
   * Returns the value of the '<em><b>Bool Const</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Bool Const</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Bool Const</em>' attribute.
   * @see #setBoolConst(String)
   * @see ctwedge.ctWedge.CtWedgePackage#getAtomicPredicate_BoolConst()
   * @model
   * @generated
   */
  String getBoolConst();

  /**
   * Sets the value of the '{@link ctwedge.ctWedge.AtomicPredicate#getBoolConst <em>Bool Const</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Bool Const</em>' attribute.
   * @see #getBoolConst()
   * @generated
   */
  void setBoolConst(String value);

  /**
   * Returns the value of the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Name</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Name</em>' attribute.
   * @see #setName(String)
   * @see ctwedge.ctWedge.CtWedgePackage#getAtomicPredicate_Name()
   * @model
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link ctwedge.ctWedge.AtomicPredicate#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

} // AtomicPredicate