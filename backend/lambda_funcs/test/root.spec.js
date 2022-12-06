import lambdaTester from 'lambda-tester'
import { handler } from '../root'

test('root returns 204 code if available', async () => {
    const event = {};
    await lambdaTester(handler)
        .event(event)
        .expectResult( (result) => {
            expect(result.statusCode).toEqual(204);
        } );
});