import { FoodNutrients } from "./FoodNutrients";



export class Food{
    username:string|undefined;
    fdcId: number|undefined;
    description: string|undefined;
    brandOwner: string|undefined;
    foodNutrients:FoodNutrients[]|undefined;
}