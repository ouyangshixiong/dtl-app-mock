/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { DtlAppMockTestModule } from '../../../test.module';
import { StoreUserUpdateComponent } from 'app/entities/store-user/store-user-update.component';
import { StoreUserService } from 'app/entities/store-user/store-user.service';
import { StoreUser } from 'app/shared/model/store-user.model';

describe('Component Tests', () => {
    describe('StoreUser Management Update Component', () => {
        let comp: StoreUserUpdateComponent;
        let fixture: ComponentFixture<StoreUserUpdateComponent>;
        let service: StoreUserService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [DtlAppMockTestModule],
                declarations: [StoreUserUpdateComponent]
            })
                .overrideTemplate(StoreUserUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(StoreUserUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(StoreUserService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new StoreUser(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.storeUser = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.update).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );

            it(
                'Should call create service on save for new entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new StoreUser();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.storeUser = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.create).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );
        });
    });
});
