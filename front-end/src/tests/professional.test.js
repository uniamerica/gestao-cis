import '@testing-library/jest-dom';

const setup = (props={}, state=null) => {
  return shallow(<App {...props} />)
}

test('Clicks in modal buttom', async () => {
  const wrapper = setup();
  const button = findByTestAttr(wrapper, 'CADASTRAR');
  button.simulate('click');
})