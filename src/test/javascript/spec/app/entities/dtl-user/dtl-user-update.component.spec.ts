/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { DtlAppMockTestModule } from '../../../test.module';
import { DTLUserUpdateComponent } from 'app/entities/dtl-user/dtl-user-update.component';
import { DTLUserService } from 'app/entities/dtl-user/dtl-user.service';
import { DTLUser } from 'app/shared/model/dtl-user.model';

describe('Component Tests', () => {
    describe('DTLUser Management Update Component', () => {
        let comp: DTLUserUpdateComponent;
        let fixture: ComponentFixture<DTLUserUpdateComponent>;
        let service: DTLUserService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [DtlAppMockTestModule],
                declarations: [DTLUserUpdateComponent]
            })
                .overrideTemplate(DTLUserUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(DTLUserUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(DTLUserService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new DTLUser(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.dTLUser = entity;
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
                    const entity = new DTLUser();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.dTLUser = entity;
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
