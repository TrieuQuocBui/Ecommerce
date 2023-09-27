import { Pipe, PipeTransform } from '@angular/core';
import { Province } from '../models/province';

@Pipe({
  name: 'province'
})
export class ProvincePipe implements PipeTransform {

  transform(value: any, args?: any): Province[] {
    if (value){
      return Object.keys(value).map(key => ({ key: key, value: value[key] })) as Province[];
    }
    return [];
  }

}
