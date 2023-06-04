import { Resource, catchError, createMemo } from "solid-js";

export class Queue<T> {
  items: Record<string, T>;
  frontIndex: number;
  backIndex: number;
  constructor() {
    this.items = {};
    this.frontIndex = 0;
    this.backIndex = 0;
  }
  enqueue(item: T) {
    this.items[this.backIndex] = item;
    this.backIndex++;
    return item + " inserted";
  }
  dequeue() {
    const item = this.items[this.frontIndex];
    delete this.items[this.frontIndex];
    this.frontIndex++;
    return item;
  }
  peek() {
    return this.items[this.frontIndex];
  }

  toArray(): Array<T> {
    return Object.values(this.items);
  }
  get printQueue() {
    return this.items;
  }
}

export const catchResource = <T>(
  resource: Resource<T>,
  onError: (e: Error) => void
) => {
  return createMemo(() => {
    return catchError(() => resource(), onError);
  });
};
